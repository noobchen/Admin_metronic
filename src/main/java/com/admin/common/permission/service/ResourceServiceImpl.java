package com.admin.common.permission.service;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Resource;
import com.admin.common.permission.repository.ResourceRepository;
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
 * Date: 12-9-7
 * Time: 下午8:48
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ResourceServiceImpl implements ResourceService {
    private final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams) {
        return resourceRepository.query(pageInfo, queryParams);
    }

    @Override
    public List<Resource> query(HashMap<String, Object> queryParams) {
        return resourceRepository.query(queryParams);
    }

    @Override
    public String add(Resource resource) {
        try {
            resourceRepository.add(resource);
            return null;
        } catch (Exception e) {
            logger.error("add resource:{},exception:{}.", resource, ExceptionUtils.getStackTrace(e));
            return "增加资源失败";
        }
    }

    @Override
    public String edit(Resource resource) {
        try {
            resourceRepository.edit(resource);
            return null;
        } catch (Exception e) {
            logger.error("edit resource:{},exception:{}.", resource, ExceptionUtils.getStackTrace(e));
            return "修改资源失败";
        }
    }

    @Override
    public String delete(Resource resource) {
        try {
            resourceRepository.delete(resource);
            return null;
        } catch (Exception e) {
            logger.error("delete resource:{},exception:{}.", resource, ExceptionUtils.getStackTrace(e));
            return "删除资源失败";
        }
    }
}
