package com.admin.common.permission.controller;

import com.admin.common.page.PageInfo;
import com.admin.common.page.PageUtils;
import com.admin.common.permission.model.Operate;
import com.admin.common.permission.model.OperateType;
import com.admin.common.permission.model.Resource;
import com.admin.common.permission.model.Role;
import com.admin.common.permission.service.ResourceService;
import com.admin.common.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-10
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("roleQuery")
    public ModelAndView query(@RequestParam(value = "name", required = false, defaultValue = "") String roleName,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setStartPageIndex(pageIndex);

        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("roleName", roleName);


        pageInfo = roleService.query(pageInfo, queryParams);

        PageUtils.set(pageInfo, "roleQuery.do", queryParams);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageInfo", pageInfo);
        HashMap<String, Object> queryResourceParams = new HashMap<String, Object>();
        queryResourceParams.put("type", "1");//模块
        model.put("moduleInfos", resourceService.query(queryResourceParams));

        model.put("name", roleName);

        return new ModelAndView("role", model);
    }

    @RequestMapping(value = "roleAdd", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam(value = "roleName") String roleName,
                            @RequestParam(value = "moduleName") String moduleId,
                            @RequestParam(value = "menuId", required = false, defaultValue = "") String[] menuId,
                            @RequestParam(value = "query", required = false, defaultValue = "") String[] query,
                            @RequestParam(value = "add", required = false, defaultValue = "") String[] add,
                            @RequestParam(value = "edit", required = false, defaultValue = "") String[] edit,
                            @RequestParam(value = "delete", required = false, defaultValue = "") String[] delete) {
        Role role = new Role();
        role.setName(roleName);
        List<Resource> resources = new ArrayList<Resource>();
        Resource module = new Resource();
        module.setId(Integer.parseInt(moduleId));
        resources.add(module);

        HashMap<String, String> queryMap = new HashMap<String, String>();
        HashMap<String, String> addMap = new HashMap<String, String>();
        HashMap<String, String> editMap = new HashMap<String, String>();
        HashMap<String, String> deleteMap = new HashMap<String, String>();

        for (String q : query) {
            queryMap.put(q, q);
        }

        for (String q : add) {
            addMap.put(q, q);
        }

        for (String q : edit) {
            editMap.put(q, q);
        }

        for (String q : delete) {
            deleteMap.put(q, q);
        }

        for (String m : menuId) {
            if (queryMap.containsKey(m) || addMap.containsKey(m) || editMap.containsKey(m) || deleteMap.containsKey(m)) {
                Resource menu = new Resource();
                menu.setId(Integer.parseInt(m));

                List<Operate> operates = new ArrayList<Operate>();
                if (queryMap.containsKey(m)) {
                    Operate operate = new Operate();
                    operate.setResourceId(Integer.parseInt(m));
                    operate.setOperateType(OperateType.QUERY);
                    operates.add(operate);
                }

                if (addMap.containsKey(m)) {
                    Operate operate = new Operate();
                    operate.setResourceId(Integer.parseInt(m));
                    operate.setOperateType(OperateType.ADD);
                    operates.add(operate);
                }

                if (editMap.containsKey(m)) {
                    Operate operate = new Operate();
                    operate.setResourceId(Integer.parseInt(m));
                    operate.setOperateType(OperateType.EDIT);
                    operates.add(operate);
                }

                if (deleteMap.containsKey(m)) {
                    Operate operate = new Operate();
                    operate.setResourceId(Integer.parseInt(m));
                    operate.setOperateType(OperateType.DELETE);
                    operates.add(operate);
                }

                menu.setOperates(operates);

                resources.add(menu);
            }
        }


        role.setResources(resources);

        String result = roleService.add(role);


        Map<String, Object> model = new HashMap<String, Object>();
        HashMap<String, Object> queryResourceParams = new HashMap<String, Object>();
        queryResourceParams.put("type", "1");//模块
        model.put("moduleInfos", resourceService.query(queryResourceParams));

        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "新建角色成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("roleQuery.do"), model);
    }

    @RequestMapping(value = "roleEdit", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam("editId") Integer id,
                             @RequestParam("editRoleName") String roleName,
                             @RequestParam("editModuleId") String moduleId,
                             @RequestParam(value = "menuId", required = false, defaultValue = "") String[] menuId,
                             @RequestParam(value = "query", required = false, defaultValue = "") String[] query,
                             @RequestParam(value = "add", required = false, defaultValue = "") String[] add,
                             @RequestParam(value = "edit", required = false, defaultValue = "") String[] edit,
                             @RequestParam(value = "delete", required = false, defaultValue = "") String[] delete) {
        Role role = new Role();
        role.setId(id);
        role.setName(roleName);

        List<Resource> resources = new ArrayList<Resource>();
        Resource module = new Resource();
        module.setId(Integer.parseInt(moduleId));
        resources.add(module);

        HashMap<String, String> queryMap = new HashMap<String, String>();
        HashMap<String, String> addMap = new HashMap<String, String>();
        HashMap<String, String> editMap = new HashMap<String, String>();
        HashMap<String, String> deleteMap = new HashMap<String, String>();

        for (String q : query) {
            queryMap.put(q, q);
        }

        for (String q : add) {
            addMap.put(q, q);
        }

        for (String q : edit) {
            editMap.put(q, q);
        }

        for (String q : delete) {
            deleteMap.put(q, q);
        }

        for (String m : menuId) {
            if (queryMap.containsKey(m) || addMap.containsKey(m) || editMap.containsKey(m) || deleteMap.containsKey(m)) {
                Resource menu = new Resource();
                menu.setId(Integer.parseInt(m));

                List<Operate> operates = new ArrayList<Operate>();
                if (queryMap.containsKey(m)) {
                    Operate operate = new Operate();
                    operate.setResourceId(Integer.parseInt(m));
                    operate.setOperateType(OperateType.QUERY);
                    operates.add(operate);
                }

                if (addMap.containsKey(m)) {
                    Operate operate = new Operate();
                    operate.setResourceId(Integer.parseInt(m));
                    operate.setOperateType(OperateType.ADD);
                    operates.add(operate);
                }

                if (editMap.containsKey(m)) {
                    Operate operate = new Operate();
                    operate.setResourceId(Integer.parseInt(m));
                    operate.setOperateType(OperateType.EDIT);
                    operates.add(operate);
                }

                if (deleteMap.containsKey(m)) {
                    Operate operate = new Operate();
                    operate.setResourceId(Integer.parseInt(m));
                    operate.setOperateType(OperateType.DELETE);
                    operates.add(operate);
                }

                menu.setOperates(operates);

                resources.add(menu);
            }
        }


        role.setResources(resources);

        String result = roleService.edit(role);

        Map<String, Object> model = new HashMap<String, Object>();
        HashMap<String, Object> queryResourceParams = new HashMap<String, Object>();
        queryResourceParams.put("type", "1");//模块
        model.put("moduleInfos", resourceService.query(queryResourceParams));
        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "修改角色成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("roleQuery.do"), model);
    }

    @RequestMapping(value = "roleDelete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("deleteId") Integer id) {
        Role role = new Role();
        role.setId(id);

        String result = roleService.delete(role);

        Map<String, Object> model = new HashMap<String, Object>();
        HashMap<String, Object> queryResourceParams = new HashMap<String, Object>();
        queryResourceParams.put("type", "1");//模块
        model.put("moduleInfos", resourceService.query(queryResourceParams));
        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "删除角色成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("roleQuery.do"), model);
    }


    @RequestMapping("getSelectMenuInfo")
    @ResponseBody
    public Object queryResourceOperate(@RequestParam("roleId") String roleId) {
        List<Resource> resources = roleService.queryResource(Integer.parseInt(roleId));

        return resources;
    }

}
