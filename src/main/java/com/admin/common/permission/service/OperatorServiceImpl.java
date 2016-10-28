package com.admin.common.permission.service;

import com.admin.login.controller.LoginController;
import com.admin.common.page.PageInfo;
import com.admin.common.permission.model.*;
import com.admin.common.permission.repository.OperatorRepository;
import com.admin.common.spring.mvc.session.SessionUtils;
import com.admin.common.util.account.PasswordUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */
@Component
public class OperatorServiceImpl implements OperatorService {
    private final Logger logger = LoggerFactory.getLogger(OperatorServiceImpl.class);
    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public String login(String account, String password) {
        Operator operator = operatorRepository.find(account);

        if (null == operator) {
            return "账号不存在。";
        } else {
            if (StringUtils.equals(PasswordUtils.decode(password), operator.getPassword())) {
                if (operator.getStatus().equals("1")) {//状态关闭
                    return "账号已停用。";
                }

                SessionUtils.saveSessionAttribute(LoginController.USER, operator);
                SessionUtils.saveLoginUser(account);//做一个标记
                List<Resource> modules = new ArrayList<Resource>();
                Map<String, List<Resource>> menus = new HashMap<String, List<Resource>>();
                Map<String, List<Operate>> operates = new HashMap<String, List<Operate>>();

                Map<String, String> existMenus = new HashMap<String, String>();

                HashMap<String, Resource> filterModules = new HashMap<String, Resource>();


                for (Role role : operator.getRoles()) {
                    List<Resource> menu = new ArrayList<Resource>();
                    String moduleUrl = null;
                    for (Resource resource : role.getResources()) {
                        if (resource.getType().equals("1")) {
                            //模块
                            if (!filterModules.containsKey(resource.getUrl())) {
                                //判断是否存在模块,不存在则加入到数组中
                                modules.add(resource);
                                filterModules.put(resource.getUrl(), resource);
                            }

                            moduleUrl = resource.getUrl();
                        } else {
                            //判断别的角色是否已经增加了此菜单
                            if (existMenus.containsKey(resource.getUrl())) {
                                List<Operate> existOperates = operates.get(resource.getUrl());
                                if (null != resource.getOperates()) {
                                    if (null == existOperates) {
                                        operates.put(resource.getUrl(), resource.getOperates());
                                    } else {
                                        for (Operate operate : resource.getOperates()) {
                                            boolean exist = false;
                                            for (Operate existOperate : existOperates) {
                                                if (operate.getOperateType().equals(existOperate.getOperateType())) {
                                                    exist = true;
                                                    break;
                                                }
                                            }
                                            if (!exist) {
                                                existOperates.add(operate);
                                            }
                                        }
                                        //排序
                                        Collections.sort(existOperates, new Comparator<Operate>() {
                                            @Override
                                            public int compare(Operate operate, Operate operate1) {
                                                if (operate.getOperateType().equals(OperateType.EDIT)) {
                                                    return -1;
                                                }
                                                if (operate.getOperateType().equals(OperateType.DELETE)) {
                                                    return 1;
                                                }
                                                return 0;
                                            }
                                        });

                                        operates.put(resource.getUrl(), existOperates);
                                    }
                                }
                            } else {
                                menu.add(resource);

                                operates.put(resource.getUrl(), resource.getOperates());

                                existMenus.put(resource.getUrl(), resource.getUrl());
                            }
                        }
                    }
                    if (menu.size() > 0) {
                        menus.put(moduleUrl, menu);
                    } else {
                        if (!menus.containsKey(moduleUrl)) {
                            menus.put(moduleUrl, new ArrayList<Resource>());
                        }
                    }
                }

                //模块排序
                Collections.sort(modules, new Comparator<Resource>() {
                    @Override
                    public int compare(Resource resource, Resource resource1) {
                        return resource.getIndex() == resource1.getIndex() ? 0 : (resource.getIndex() > resource1.getIndex() ? 1 : -1);
                    }
                });

                //菜单排序
                for (List<Resource> menu : menus.values()) {
                    Collections.sort(menu, new Comparator<Resource>() {
                        @Override
                        public int compare(Resource resource, Resource resource1) {
                            return resource.getIndex() == resource1.getIndex() ? 0 : (resource.getIndex() > resource1.getIndex() ? 1 : -1);
                        }
                    });
                }

                for (Resource module : modules) {
                    String moduleUrl = module.getUrl();

                    List<Resource> subMenus = menus.get(moduleUrl);

                    module.setSubMenus(subMenus);
                }

                SessionUtils.saveSessionAttribute(LoginController.MODULE, modules);
                SessionUtils.saveSessionAttribute(LoginController.MENUS, menus);
                SessionUtils.saveSessionAttribute(LoginController.OPERATES, operates);

                return null;
            } else {
                return "账号或密码错误。";
            }
        }
    }

    @Override
    public PageInfo query(PageInfo pageInfo, HashMap<String, Object> queryParams) {
        return operatorRepository.query(pageInfo, queryParams);
    }

    @Override
    public List<Operator> query(HashMap<String, Object> queryParams) {
        return operatorRepository.query(queryParams);
    }

//    @Override
//    public List<Resource> queryResource(HashMap<String, Object> queryParams) {
//         return operatorRepository.queryResource(queryParams);
//    }

    @Override
    public String add(Operator operator) {
        try {
            operatorRepository.add(operator);
            return null;
        } catch (Exception e) {
            logger.error("add operator:{},exception:{}.", operator, ExceptionUtils.getStackTrace(e));
            return "增加用户失败";
        }
    }

    @Override
    public String edit(Operator operator) {
        try {
            operatorRepository.edit(operator);
            return null;
        } catch (Exception e) {
            logger.error("add operator:{},exception:{}.", operator, ExceptionUtils.getStackTrace(e));
            return "修改用户失败";
        }
    }

    @Override
    public String delete(Operator operator) {
        try {
            operatorRepository.delete(operator);
            return null;
        } catch (Exception e) {
            logger.error("add operator:{},exception:{}.", operator, ExceptionUtils.getStackTrace(e));
            return "删除用户失败";
        }
    }
}
