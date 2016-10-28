package com.admin.common.permission.service;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Resource;
import com.admin.common.permission.model.Role;
import com.admin.common.permission.repository.RoleRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-10
 * Time: 下午10:11
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RoleServiceImpl implements RoleService {
    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams) {
        return roleRepository.query(pageInfo, queryParams);
    }

    @Override
    public List<Role> query(HashMap<String, Object> queryParams) {
        return roleRepository.query(queryParams);
    }

    @Override
    public List<Resource> queryResource(Integer roleId) {
        return roleRepository.queryResource(roleId);
    }

    @Override
    public String add(Role role) {
        try {
            roleRepository.add(role);
            return null;
        } catch (Exception e) {
            logger.error("add role:{},exception:{}.", role, ExceptionUtils.getStackTrace(e));
            return "增加角色失败";
        }
    }

    @Override
    public String edit(Role role) {
        try {
            roleRepository.edit(role);
            return null;
        } catch (Exception e) {
            logger.error("edit role:{},exception:{}.", role, ExceptionUtils.getStackTrace(e));
            return "修改角色失败";
        }
    }

    @Override
    public String delete(Role role) {
        try {
            roleRepository.delete(role);
            return null;
        } catch (Exception e) {
            logger.error("delete role:{},exception:{}.", role, ExceptionUtils.getStackTrace(e));
            return "删除角色失败";
        }
    }
}
