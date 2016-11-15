package com.admin.common.page;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.ibatis.type.UnknownTypeHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * Author: Tony.Wang
 * Date: 12-5-24
 * Time: 下午2:43
 * Description: to write something
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MysqlPagingPlugin implements Interceptor {
    //private String dialect;

    @SuppressWarnings("unchecked")
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!(invocation.getTarget() instanceof RoutingStatementHandler))
            return invocation.proceed();

        RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        //分析是否含有分页参数，如果没有则不是分页查询
        //注意：在多参数的情况下，只处理第一个分页参数
        PageInfo pageInfo = null;
        Object paramObj = boundSql.getParameterObject();
        if (paramObj instanceof PageInfo) { //只有一个参数的情况
            pageInfo = (PageInfo) paramObj;
        } else if (paramObj instanceof Map) { //多参数的情况，找到第一个Page的参数
            for (Map.Entry<String, Object> e : ((Map<String, Object>) paramObj).entrySet()) {
                if (e.getValue() instanceof PageInfo) {
                    pageInfo = (PageInfo) e.getValue();
                    break;
                }
            }
        }

        if (null == pageInfo)
            return invocation.proceed();

        //查找总记录数，并设置Page的相关参数
        long total = this.getTotal(invocation);
        pageInfo.setTotalRow(total);
        pageInfo.setTotalPageSize(total / pageInfo.getPageSize() + ((total % pageInfo.getPageSize() > 0) ? 1 : 0));
        //生成分页SQL
        String pageSql = generatePageSql(boundSql.getSql(), pageInfo);
        //强制修改最终要执行的SQL
        setFieldValue(boundSql, "sql", pageSql);
        return invocation.proceed();
    }

    /**
     * 获取记录总数
     */
    @SuppressWarnings("unchecked")
    private long getTotal(Invocation invocation) throws Exception {
        RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        /*
        * 为了设置查找总数SQL的参数，必须借助MappedStatement、Configuration等这些类，
        * 但statementHandler并没有开放相应的API，所以只好用反射来强行获取。
        */
        BaseStatementHandler delegate = (BaseStatementHandler) getFieldValue(statementHandler, "delegate");
        MappedStatement mappedStatement = (MappedStatement) getFieldValue(delegate, "mappedStatement");
        Configuration configuration = mappedStatement.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        Object param = boundSql.getParameterObject();
        MetaObject metaObject = configuration.newMetaObject(param);

        long total = 0;
        String sql = boundSql.getSql();
        String countSql = "select count(1) from (" + sql + ") as t"; //记录统计  (mysql要求必须添加 最后的as t)
        try {
            Connection conn = (Connection) invocation.getArgs()[0];
            PreparedStatement ps = conn.prepareStatement(countSql);
            int i = 1;
            for (ParameterMapping pm : boundSql.getParameterMappings()) {
                Object value = null;
                String propertyName = pm.getProperty();
                PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                if (typeHandlerRegistry.hasTypeHandler(param.getClass())) {
                    value = param;
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    value = boundSql.getAdditionalParameter(propertyName);
                } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                    value = boundSql.getAdditionalParameter(prop.getName());
                    if (value != null) {
                        value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                    }
                } else {
                    value = metaObject.getValue(propertyName);
                }

                UnknownTypeHandler typeHandler = (UnknownTypeHandler) pm.getTypeHandler();
                typeHandler.setParameter(ps, i++, value, pm.getJdbcType());
            }
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getLong(1);
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("分页查询无法获取总记录数", e);
        }
        return total;
    }

    /**
     * 生成分页SQL
     */
    private String generatePageSql(String sql, PageInfo pageInfo) {
        StringBuilder pageSql = new StringBuilder();
        pageSql.append(sql);
        pageSql.append(" limit ").append(pageInfo.getStartPageIndex() * pageInfo.getPageSize()).append(",").append(pageInfo.getPageSize());

        return pageSql.toString();
    }

    /**
     * 用反射取对象的属性值
     */
    private Object getFieldValue(Object obj, String fieldName) throws Exception {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(obj);
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 用反射设置对象的属性值
     */
    private void setFieldValue(Object obj, String fieldName, Object fieldValue) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, fieldValue);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties props) {

    }
}
