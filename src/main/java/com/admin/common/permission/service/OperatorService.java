package com.admin.common.permission.service;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Operator;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午7:39
 * To change this template use File | Settings | File Templates.
 */
public interface OperatorService {
    public String login(String account, String password);

    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams);

    public List<Operator> query(HashMap<String, Object> queryParams);

   // public List<Resource> queryResource(HashMap<String, Object> queryParams);

    public String add(Operator operator);

    public String edit(Operator operator);

    public String delete(Operator operator);
}
