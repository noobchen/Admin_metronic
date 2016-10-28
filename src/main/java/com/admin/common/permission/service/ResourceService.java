package com.admin.common.permission.service;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午8:47
 * To change this template use File | Settings | File Templates.
 */
public interface ResourceService {
    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams);

    public List<Resource> query(HashMap<String, Object> queryParams);

    public String add(Resource resource);

    public String edit(Resource resource);

    public String delete(Resource resource);
}
