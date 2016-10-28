package com.admin.common.permission.repository;

import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.Operator;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */
public interface OperatorRepository {
    public Operator find(String account);

    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams);

    public List<Operator> query(HashMap<String, Object> queryParams);

   // public List<Resource> queryResource(HashMap<String, Object> queryParams);

    public void edit(Operator operator);

    public void delete(Operator operator);

    public void add(Operator operator);
}
