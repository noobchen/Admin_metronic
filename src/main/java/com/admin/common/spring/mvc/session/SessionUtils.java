package com.admin.common.spring.mvc.session;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午3:01
 * To change this template use File | Settings | File Templates.
 */
public class SessionUtils {
    private static ConcurrentHashMap<String, String> users = new ConcurrentHashMap<String, String>();

    public static void removeLoginUser(String account) {
        users.remove(account);
    }

    public static void saveLoginUser(String account) {
        users.put(account, String.valueOf((int) (Math.random() * 900) + 100));
    }

    public static String getLoginUser(String account) {
        return users.get(account);
    }

    public static String getContextPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    }


    public static void saveSessionAttribute(String key, Object value) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(key, value);
    }

    public static void removeSessionAllAttribute() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().invalidate();
    }

    public static Object getSessionAttribute(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession().getAttribute(key);
    }

    public static void removeSessionAttribute(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().removeAttribute(key);
    }

    public static void saveRequestAttribute(String key, Object value) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute(key, value);
    }

    public static Object getRequestAttribute(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getAttribute(key);
    }

    public static void removeRequestAttribute(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.removeAttribute(key);
    }

    public static String getRequestParam(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameter(key);
    }
}
