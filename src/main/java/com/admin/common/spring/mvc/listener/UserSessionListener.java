package com.admin.common.spring.mvc.listener;

import com.admin.login.controller.LoginController;
import com.admin.common.permission.model.Operator;
import com.admin.common.spring.mvc.session.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-28
 * Time: 下午8:47
 * To change this template use File | Settings | File Templates.
 */
public class UserSessionListener implements HttpSessionListener {
    private final Logger logger = LoggerFactory.getLogger(UserSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.debug("session:{} created.", httpSessionEvent);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.debug("session:{} destroyed.", httpSessionEvent);
        Operator operator = (Operator) httpSessionEvent.getSession().getAttribute(LoginController.USER);
        if (null != operator) {
            logger.debug("remove login user:{}.", operator.getAccount());
            SessionUtils.removeLoginUser(operator.getAccount());
        }
    }
}
