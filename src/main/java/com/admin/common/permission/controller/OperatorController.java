package com.admin.common.permission.controller;

import com.admin.common.page.PageInfo;
import com.admin.common.page.PageUtils;
import com.admin.common.permission.model.Operator;
import com.admin.common.permission.model.Role;
import com.admin.common.permission.service.OperatorService;
import com.admin.common.permission.service.RoleService;
import com.admin.common.util.account.AccountUtils;
import com.admin.common.util.account.PasswordUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-11
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class OperatorController {
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("operatorQuery")
    public ModelAndView query(@RequestParam(value = "name", required = false, defaultValue = "") String operatorName,
                              @RequestParam(value = "company", required = false, defaultValue = "") String company,
                              @RequestParam(value = "roleName", required = false, defaultValue = "") String roleName,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setStartPageIndex(pageIndex);

        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("operatorName", operatorName);
        queryParams.put("roleName", roleName);
        queryParams.put("operatorCompany", company);


        pageInfo = operatorService.query(pageInfo, queryParams);

        PageUtils.set(pageInfo, "operatorQuery.do", queryParams);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageInfo", pageInfo);
        model.put("roleList", roleService.query(new HashMap<String, Object>()));
        model.put("name", operatorName);
        model.put("company", company);

        return new ModelAndView("operator", model);
    }

    @RequestMapping(value = "operatorAdd", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam("operatorName") String operatorName,
                            @RequestParam("operatorCompany") String operatorCompany,
                            @RequestParam("accountType") String accountType,
                            @RequestParam(value = "accountTypeId", required = false, defaultValue = "") Integer accountTypeId,
                            @RequestParam(value = "operatorEmail", required = false, defaultValue = "") String operatorEmail,
                            @RequestParam(value = "operatorPhone", required = false, defaultValue = "") String operatorPhone,
                            @RequestParam(value = "remark", required = false, defaultValue = "") String remark,
                            @RequestParam(value = "roles") Integer[] roles) {

        Operator operator = new Operator();
        operator.setOperatorName(operatorName);
        operator.setOperatorCompany(operatorCompany);
        operator.setOperatorEmail(operatorEmail);
        operator.setOperatorPhone(operatorPhone);
        operator.setAccount(AccountUtils.getCustomerAccount());
        operator.setPassword(PasswordUtils.decode(operator.getAccount()));
        operator.setRemark(remark);
        operator.setAccountType(accountType);
        if (!StringUtils.equals(accountType, "0")) {//不是管理员
            operator.setAccountTypeId(accountTypeId);
        }

        if (null != roles) {
            List<Role> roleList = new ArrayList<Role>();
            for (Integer id : roles) {
                Role role = new Role();
                role.setId(id);
                roleList.add(role);
            }

            operator.setRoles(roleList);
        }


        String result = operatorService.add(operator);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("roleList", roleService.query(new HashMap<String, Object>()));
        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "新建用户成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("operatorQuery.do"), model);
    }

    @RequestMapping(value = "operatorEdit", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam("editId") Integer id,
                             @RequestParam("editOperatorName") String operatorName,
                             @RequestParam("editOperatorCompany") String operatorCompany,
                             @RequestParam("editAccountType") String accountType,
                             @RequestParam(value = "editAccountTypeId", required = false, defaultValue = "") Integer accountTypeId,
                             @RequestParam(value = "editOperatorEmail", required = false, defaultValue = "") String operatorEmail,
                             @RequestParam(value = "editOperatorPhone", required = false, defaultValue = "") String operatorPhone,
                             @RequestParam(value = "editRemark", required = false, defaultValue = "") String remark,
                             @RequestParam(value = "editRoles") Integer[] roles,
                             @RequestParam(value = "editStatus") String status,
                             @RequestParam(value = "editPassword") String password) {
        Operator operator = new Operator();
        operator.setId(id);
        operator.setStatus(status);
        if (StringUtils.isEmpty(password)) {
            operator.setPassword(password);
        } else {
            operator.setPassword(PasswordUtils.decode(password));
        }


        operator.setOperatorName(operatorName);
        operator.setOperatorCompany(operatorCompany);
        operator.setOperatorEmail(operatorEmail);
        operator.setOperatorPhone(operatorPhone);
        operator.setRemark(remark);
        operator.setAccountType(accountType);
        if (!StringUtils.equals(accountType, "0")) {//不是管理员
            operator.setAccountTypeId(accountTypeId);
        }

        if (null != roles) {
            List<Role> roleList = new ArrayList<Role>();
            for (Integer roleId : roles) {
                Role role = new Role();
                role.setId(roleId);
                roleList.add(role);
            }

            operator.setRoles(roleList);
        }

        String result = operatorService.edit(operator);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("roleList", roleService.query(new HashMap<String, Object>()));
        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "修改用户成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("operatorQuery.do"), model);
    }


    @RequestMapping(value = "operatorDelete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("deleteId") Integer id) {
        Operator operator = new Operator();
        operator.setId(id);

        String result = operatorService.delete(operator);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("roleList", roleService.query(new HashMap<String, Object>()));
        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "删除用户成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("operatorQuery.do"), model);
    }

}
