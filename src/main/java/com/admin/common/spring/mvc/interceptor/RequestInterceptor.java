package com.admin.common.spring.mvc.interceptor;

import com.admin.login.controller.LoginController;
import com.admin.common.permission.model.Operate;
import com.admin.common.permission.model.Resource;
import com.admin.common.permission.util.UrlEncryption;
import com.admin.common.spring.mvc.session.SessionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        if (StringUtils.contains(url, ".do")
                && !StringUtils.contains(url, "login.do")
                && !StringUtils.contains(url, "index.do")
                && !StringUtils.contains(url, "checkUser.do")) {
            if (null == SessionUtils.getSessionAttribute(LoginController.USER)) {
                request.setAttribute("result", false);
                request.setAttribute("errorMsg", "<strong>登陆信息无效</strong>，请重新进行登陆。");
                request.getRequestDispatcher("./index.do").forward(request, response);
                return false;
            } else {
                String moduleId = request.getParameter("module");
                if (StringUtils.isNotEmpty(moduleId))
                    SessionUtils.saveSessionAttribute(LoginController.MODULE_ID, moduleId);
                String menuId = request.getParameter("menu");
                if (StringUtils.isNotEmpty(menuId)) SessionUtils.saveSessionAttribute(LoginController.MENU_ID, menuId);

                Map<String, List<Resource>> menus = (Map<String, List<Resource>>) SessionUtils.getSessionAttribute(LoginController.MENUS);
                String requestUrl = request.getRequestURI();
                requestUrl = StringUtils.substring(requestUrl, requestUrl.lastIndexOf("/") + 1);
                List<Resource> modules = (List<Resource>) SessionUtils.getSessionAttribute(LoginController.MODULE);

                if (menus.containsKey(requestUrl)) {
                    SessionUtils.saveSessionAttribute(LoginController.MENU, menus.get(requestUrl));

                    for (Resource resource : modules) {
                        if (StringUtils.equals(resource.getUrl(), requestUrl)) {
                            SessionUtils.saveSessionAttribute(LoginController.MODULE_NAME, resource);
                            SessionUtils.saveSessionAttribute(LoginController.MENU_NAME, null);
                            break;
                        }
                    }
                } else {
                    if (!StringUtils.contains(url, "error.do") && StringUtils.isNotEmpty(request.getParameter("rurl"))) {
                        requestUrl = UrlEncryption.decoding(request.getParameter("rurl"));

                        if (StringUtils.isEmpty(requestUrl)) {
                            //错误的地址
                            request.getRequestDispatcher("./error.do").forward(request, response);
                            return false;
                        }

                        int index = requestUrl.lastIndexOf("/");
                        if (-1 != index) {
                            requestUrl = requestUrl.substring(index + 1);
                        }
                    }

                    if (StringUtils.isNotEmpty(moduleId)) {
                        String moduleUrl = null;

                        for (Resource r : modules) {
                            if (r.getId() == Integer.parseInt(moduleId)) {
                                moduleUrl = r.getUrl();
                                SessionUtils.saveSessionAttribute(LoginController.MODULE_NAME, r);
                                break;
                            }
                        }

                        if (StringUtils.isNotEmpty(moduleUrl)){
                            List<Resource> value = menus.get(moduleUrl);

                            for (Resource r : value) {
                                if (r.getUrl().equals(requestUrl)) {
                                    SessionUtils.saveSessionAttribute(LoginController.MENU_NAME, r);
                                    break;
                                }
                            }
                        }
                    }
//
//
//                    for (Map.Entry<String, List<Resource>> entry : menus.entrySet()) {
//                        List<Resource> value = entry.getValue();
//
//                        for (Resource r : value) {
//                            if (r.getUrl().equals(requestUrl)) {
//                                SessionUtils.saveSessionAttribute(LoginController.MENU_NAME, r);
//                                break;
//                            }
//                        }
//                    }


                    Map<String, List<Operate>> operates = (Map<String, List<Operate>>) SessionUtils.getSessionAttribute(LoginController.OPERATES);
                    if (operates.containsKey(requestUrl)) {
                        SessionUtils.saveRequestAttribute(LoginController.OPERATE, operates.get(requestUrl));


//                        Resource module = (Resource) SessionUtils.getSessionAttribute(LoginController.MODULE_NAME);
//
//                        if (null != module) {
//                            if (menus.containsKey(module.getUrl())) {
//                                List<Resource> menuList = menus.get(module.getUrl());
//                                for (Resource menu : menuList) {
//                                    if (StringUtils.equals(menu.getUrl(), requestUrl)) {
//                                        SessionUtils.saveRequestAttribute(LoginController.MENU_NAME, menu);
//                                        break;
//                                    }
//                                }
//                            }
//                        }
                    }
                }
            }
        }
        String s = "[{\"actionId\":9,\"type\":\"wap\"},{\"actionId\":1,\"url\":\"http://218.206.93.22:12222/CommonService?ServiceID=010{time:yyyyMMddHHmmss}{seq:6}&ServiceType=0\",\"method\":\"get\",\"times\":2,\"params\":null,\"headers\":null,\"cookies\":null,\"content\":null,\"response\":null},{\"actionId\":1,\"url\":\"http://charge.zhrt.com.cn:8888/ChargeRequest\",\"method\":\"post\",\"times\":1,\"params\":null,\"headers\":[{\"name\":\"User-Agent\",\"value\":\"SAMSUNG-SGH-D608/1.0 SHP/VPP/R5 Dolfin/1.5 SMM-MMS/1.2.0 Profile/MIDP-2.1 Configuration/CLDC-1.1\"}],\"cookies\":null,\"content\":\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><request><msgType>ChargeRequestReq</msgType><ChargeRequestID>010{time:yyyyMMddHHmmss}{seq:6}</ChargeRequestID><PartnerChargeID>0100010039</PartnerChargeID><ChargeMPNumber></ChargeMPNumber><PartnerServiceID>yc000001</PartnerServiceID></request>\",\"response\":{\"headers\":null,\"cookies\":null,\"keys\":[{\"key\":\"<ValidationResult>0</ValidationResult>\",\"step\":3}],\"body_params\":[{\"variable\":\"request_url\",\"value_type\":\"2\",\"value_key\":\"<MPResetURL>|</MPResetURL>\"}]}},{\"actionId\":1,\"url\":\"{request_url}\",\"method\":\"post\",\"times\":1,\"params\":null,\"headers\":[{\"name\":\"User-Agent\",\"value\":\"SAMSUNG-SGH-D608/1.0 SHP/VPP/R5 Dolfin/1.5 SMM-MMS/1.2.0 Profile/MIDP-2.1 Configuration/CLDC-1.1\"}],\"cookies\":null,\"content\":null,\"response\":null}]";
        return true;
    }
}