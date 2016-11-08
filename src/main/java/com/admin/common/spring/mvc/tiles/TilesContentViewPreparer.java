package com.admin.common.spring.mvc.tiles;

import com.admin.login.controller.LoginController;
import com.admin.common.permission.model.Operate;
import com.admin.common.permission.model.Operator;
import com.admin.common.permission.util.UrlEncryption;
import com.admin.common.spring.mvc.session.SessionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-18
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
public class TilesContentViewPreparer implements ViewPreparer {
    @Override
    public void execute(Request tilesRequestContext, AttributeContext attributeContext) {
        String url = SessionUtils.getRequestParam("rurl");
        if (StringUtils.isNotEmpty(url)) {
            url = UrlEncryption.decoding(url);

            int index = url.lastIndexOf("/");

            String requestUrl = url.substring(index + 1);

            Operator operator = (Operator) SessionUtils.getSessionAttribute(LoginController.USER);
            //增加账号和权限参数
            String param = "";
            param = "account=" + operator.getAccount() + "&r=" + SessionUtils.getLoginUser(operator.getAccount());

            param = param + "&accountType=" + operator.getAccountType()
                    + "&accountTypeId=" + (operator.getAccountTypeId() == null ? "" : operator.getAccountTypeId());

            param = param + "&backUrl=" + SessionUtils.getContextPath() + "checkUser.do";

            Map<String, List<Operate>> operates = (Map<String, List<Operate>>) SessionUtils.getSessionAttribute(LoginController.OPERATES);
            if (operates.containsKey(requestUrl)) {
                List<Operate> operateList = operates.get(requestUrl);
                for (Operate operate : operateList) {
                    param = param + "&operateType=" + operate.getOperateType();
                }
            }

            if (url.contains("?")) {
                url = url + "&infoParam=" + UrlEncryption.encrypt(param);
            } else {
                url = url + "?infoParam=" + UrlEncryption.encrypt(param);
            }

            attributeContext.putAttribute("body", new Attribute(url));
            SessionUtils.saveRequestAttribute("tilesFrameFlag", true);
        }
    }
}
