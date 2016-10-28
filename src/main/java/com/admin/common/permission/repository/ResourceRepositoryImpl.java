package com.admin.common.permission.repository;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Operate;
import com.admin.common.permission.model.Resource;
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
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ResourceRepositoryImpl extends SqlSessionTemplate implements ResourceRepository {
    @Autowired
    public ResourceRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams) {
        queryParams.put("pageInfo", pageInfo);
        List<Resource> list = (List<Resource>) selectList("resource.query", queryParams);
        pageInfo.setResult(list);
        return pageInfo;
    }

    @Override
    public List<Resource> query(HashMap<String, Object> queryParams) {
        return (List<Resource>) selectList("resource.query", queryParams);
    }

    @Override
    public Resource find(Integer id) {
        return (Resource) selectOne("resource.find", id);
    }

    @Override
    public void add(Resource resource) {
        insert("resource.add", resource);
        if (null != resource.getOperates()) {
            for (Operate operate : resource.getOperates()) {
                operate.setResourceId(resource.getId());
                insert("resource.addOperate", operate);
            }
        }
    }

    @Override
    public void edit(Resource resource) {
        update("resource.edit", resource);
        delete("resource.deleteOperate", resource.getId());
        for (Operate operate : resource.getOperates()) {
            insert("resource.addOperate", operate);
        }
    }

    @Override
    public void delete(Resource resource) {
        delete("resource.deleteOperate", resource.getId());
        delete("resource.delete", resource);
        delete("resource.deleteAllChildrenOperate",resource.getId());
        delete("resource.deleteAllChildren",resource);
        delete("role.deleteRoleResource",resource);
        delete("role.deleteRoleResourceOperate", resource);
    }
}
