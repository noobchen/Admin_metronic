package com.admin.common.permission.repository;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
public interface ResourceRepository {
    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams);

    public List<Resource> query(HashMap<String, Object> queryParams);

    public Resource find(Integer id);

    public void add(Resource resource);

    public void edit(Resource resource);

    public void delete(Resource resource);
}