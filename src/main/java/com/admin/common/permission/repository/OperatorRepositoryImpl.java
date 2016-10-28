package com.admin.common.permission.repository;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Operator;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午7:42
 * To change this template use File | Settings | File Templates.
 */
@Component
public class OperatorRepositoryImpl extends SqlSessionTemplate implements OperatorRepository {
    @Autowired
    public OperatorRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public Operator find(String account) {
        return (Operator) selectOne("operator.find", account);
    }

    @Override
    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams) {
        queryParams.put("pageInfo", pageInfo);
        List<Operator> operators = (List<Operator>) selectList("operator.query", queryParams);
        pageInfo.setResult(operators);
        return pageInfo;
    }

    @Override
    public List<Operator> query(HashMap<String, Object> queryParams) {
        return (List<Operator>) selectList("operator.query", queryParams);
    }

//    @Override
//    public List<Resource> queryResource(HashMap<String, Object> queryParams) {
//        return (List<Resource>) selectList("operator.queryResource", queryParams);
//    }

    @Override
    public void edit(Operator operator) {
        update("operator.edit", operator);
        if (null != operator.getRoles()) {
            delete("operator.deleteRole", operator);
            insert("operator.addRole", operator);
        }
    }

    @Override
    public void delete(Operator operator) {
        delete("operator.delete", operator);
        delete("operator.deleteRole", operator);
    }

    @Override
    public void add(Operator operator) {
        insert("operator.add", operator);
        insert("operator.addRole", operator);
    }
}
