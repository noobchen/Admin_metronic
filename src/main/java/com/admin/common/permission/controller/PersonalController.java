package com.admin.common.permission.controller;

import com.admin.login.controller.LoginController;
import com.admin.common.page.PageInfo;
import com.admin.common.page.PageUtils;
import com.admin.common.permission.model.Operator;
import com.admin.common.permission.service.OperatorService;
import com.admin.common.spring.mvc.session.SessionUtils;
import com.admin.common.util.account.PasswordUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-18
 * Time: 下午7:43
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PersonalController {
    @Autowired
    private OperatorService operatorService;

    @RequestMapping("personalQuery")
    public ModelAndView query(@RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setStartPageIndex(pageIndex);

        HashMap<String, Object> queryParams = new HashMap<String, Object>();

        Operator operator = (Operator) SessionUtils.getSessionAttribute(LoginController.USER);
        queryParams.put("account", operator.getAccount());


        pageInfo = operatorService.query(pageInfo, queryParams);

        PageUtils.set(pageInfo, "personalQuery.do", queryParams);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageInfo", pageInfo);

        return new ModelAndView("personal", model);
    }

    @RequestMapping(value = "personalEdit", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam("editId") Integer id,
                             @RequestParam("editOperatorName") String operatorName,
                             @RequestParam("editOperatorCompany") String operatorCompany,
                             @RequestParam(value = "editOperatorEmail", required = false, defaultValue = "") String operatorEmail,
                             @RequestParam(value = "editOperatorPhone", required = false, defaultValue = "") String operatorPhone,
                             @RequestParam(value = "editRemark", required = false, defaultValue = "") String remark,
                             @RequestParam(value = "editPassword") String password) {
        Operator operator = new Operator();
        operator.setId(id);
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

        String result = operatorService.edit(operator);

        Map<String, Object> model = new HashMap<String, Object>();
        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "修改用户成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("personalQuery.do"), model);
    }
}
