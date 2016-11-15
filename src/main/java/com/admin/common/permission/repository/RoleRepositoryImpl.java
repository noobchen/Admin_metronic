package com.admin.common.permission.repository;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Resource;
import com.admin.common.permission.model.Role;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-10
 * Time: 下午10:01
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RoleRepositoryImpl extends SqlSessionTemplate implements RoleRepository {
    @Autowired
    public RoleRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams) {
        queryParams.put("pageInfo", pageInfo);
        List<Role> list = selectList("role.query", queryParams);
        pageInfo.setResult(list);
        return pageInfo;
    }

    @Override
    public List<Role> query(HashMap<String, Object> queryParams) {
        return  selectList("role.query", queryParams);
    }

    @Override
    public List<Resource> queryResource(Integer roleId) {
        return selectList("role.findResources",roleId);
    }

    @Override
    public Role find(Integer id) {
        return (Role) selectOne("role.find", id);
    }

    @Override
    public void add(Role role) {
        insert("role.add", role);
        insert("role.addResource", role);
        for (Resource resource : role.getResources()) {
            if (null != resource.getOperates() && resource.getOperates().size() > 0) {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("roleId", role.getId());
                params.put("resourceId", resource.getId());
                params.put("operates", resource.getOperates());
                insert("role.addResourceOperate", params);
            }
        }
    }

    @Override
    public void edit(Role role) {
        update("role.edit", role);
        delete("role.deleteResource",role);
        delete("role.deleteResourceOperate",role);

        insert("role.addResource", role);
        for (Resource resource : role.getResources()) {
            if (null != resource.getOperates() && resource.getOperates().size() > 0) {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("roleId", role.getId());
                params.put("resourceId", resource.getId());
                params.put("operates", resource.getOperates());
                insert("role.addResourceOperate", params);
            }
        }
    }

    @Override
    public void delete(Role role) {
        delete("role.delete", role);
        delete("role.deleteResource",role);
        delete("role.deleteResourceOperate",role);
        delete("operator.deleteOperatorRole", role);
    }
}
