<%--
  Created by IntelliJ IDEA.
  User: cyc
  Date: 2016/10/26
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- BEGIN PAGE TITLE-->
<h3 class="page-title">角色管理
    <small> 1：配置角色管理资源；</small>
    <small> 2：配置角色访问资源权限；</small>
</h3>
<c:if test="${null != param.result}">
    <c:if test="${false == param.result}">
        <div class="alert alert-danger">
            <button class="close" data-close="alert"></button>
            <span>${param.errorMsg}</span>
        </div>
    </c:if>
    <c:if test="${true == param.result}">
        <div class="alert alert-success">
            <button class="close" data-close="alert"></button>
            <span>${param.errorMsg}</span>
        </div>
    </c:if>
</c:if>
<!-- END PAGE TITLE-->

<c:if test="${null != pageInfo}">
    <div class="row">
        <div class="col-md-12">
            <!-- BEGIN EXAMPLE TABLE PORTLET-->
            <div class="portlet light portlet-fit bordered">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-settings font-red"></i>
    <span class="caption-subject font-red sbold">
    设 置
    </span>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-6">
                                <c:if test="${null != operate}">
                                    <c:forEach var="item" items="${operate}" step="1">
                                        <c:if test="${item.operateType == 'ADD'}">
                                            <div class="btn-group">
                                                <button class="btn green" data-toggle="modal" href="#add"> 添 加
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </div>
                            <div class="col-md-6">
                                <div class="btn-group pull-right">
                                    <button class="btn green btn-outline dropdown-toggle" data-toggle="dropdown">
                                        Tools
                                        <i class="fa fa-angle-down"></i>
                                    </button>
                                    <ul class="dropdown-menu pull-right">
                                        <li>
                                            <a href="javascript:;"> Print </a>
                                        </li>
                                        <li>
                                            <a href="javascript:;"> Save as PDF </a>
                                        </li>
                                        <li>
                                            <a href="javascript:;"> Export to Excel </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover table-bordered" id="role_table">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>角色名称</th>
                            <th>建立时间</th>
                            <th style="display: none">模块</th>
                            <th style="display: none">模块</th>
                            <th> 修改</th>
                            <th> 删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${null != pageInfo.result}">
                            <c:forEach var="item" items="${pageInfo.result}" step="1">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td>${item.createTime}</td>
                                    <td style="display: none">
                                        <c:if test="${null != item.resources}">
                                            <c:forEach var="resource" items="${item.resources}" step="1">
                                                <c:if test="${resource.type == '1'}">
                                                    ${resource.resourceName}
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <td style="display: none">
                                        <c:if test="${null != item.resources}">
                                            <c:forEach var="resource" items="${item.resources}" step="1">
                                                <c:if test="${resource.type == '1'}">
                                                    ${resource.id}
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <c:if test="${null != operate}">
                                        <c:forEach var="item" items="${operate}" step="1">
                                            <c:if test="${item.operateType == 'EDIT'}">
                                                <td>
                                                    <a class="btn btn-warning" data-toggle="modal" href="#edit"
                                                       name="editButton"> 修 改
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                </td>
                                            </c:if>
                                            <c:if test="${item.operateType == 'DELETE'}">
                                                <td>
                                                    <a class="btn btn-danger" data-toggle="modal" href="#delete" name="delButton"> 删 除
                                                        <i class="fa fa-remove"></i>
                                                    </a>

                                                </td>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- END EXAMPLE TABLE PORTLET-->
        </div>
    </div>

</c:if>

<div id="add" class="modal fade" tabindex="-1">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

        <h3>新 建</h3>
    </div>
    <div class="portlet-body form">
        <div class="form-body">
            <form action="roleAdd.do" method="post" id="addForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="roleName" name="roleName">
                            <label for="roleName">角色名称</label>
                            <span class="help-block">管理资源角色名称</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label has-info">
                            <select class="form-control edited" id="moduleName" name="moduleName">
                                <option value="">请选择模块</option>
                                <c:if test="${null != moduleInfos}">
                                    <c:forEach var="item" items="${moduleInfos}" step="1">
                                        <option value="${item.id}">${item.resourceName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <label for="moduleName">角色管理菜单所在模块</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <table class="table table-hover" id="menuName">
                                <thead>
                                <tr>
                                    <th>资源名称</th>
                                    <th>权限</th>
                                </tr>
                                </thead>
                                <tbody id="menuNameBody">
                                </tbody>
                            </table>
                            <label for="menuName">选择角色管理菜单及权限</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-success"
                               value="新 建"/>
                        <input type="reset" class="btn btn-danger" data-dismiss="modal"
                               value="关 闭"/>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>


<div id="edit" class="modal fade" tabindex="-1">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

        <h3>修 改</h3>
    </div>
    <div class="portlet-body form">
        <div class="form-body">
            <form action="roleEdit.do" method="post" id="editForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editId" name="editId" readonly="true">
                            <label for="editId">编号</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editRoleName" name="editRoleName">
                            <label for="editRoleName">角色名称</label>
                            <span class="help-block">管理资源角色名称</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label" >
                            <input type="text" class="form-control" id="editModuleName" name="editModuleName" value="" readonly="readonly">
                            <input type="hidden" class="form-control" id="editModuleId" name="editModuleId" value="" readonly="readonly">
                            <label for="editModuleName">管理模块</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <table class="table table-hover" id="editMenuName">
                                <thead>
                                <tr>
                                    <th>资源名称</th>
                                    <th>权限</th>
                                </tr>
                                </thead>
                                <tbody id="editMenuNameBody">
                                </tbody>
                            </table>
                            <label for="editMenuName">选择角色管理菜单及权限</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-success"
                               value="修 改"/>
                        <input type="reset" class="btn btn-danger" data-dismiss="modal"
                               value="关 闭"/>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>


<div id="delete" class="modal fade" tabindex="-1">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

        <h3>删 除</h3>
    </div>
    <div class="portlet-body form">
        <div class="form-body">
            <form action="roleDelete.do" method="post" id="deleteForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="deleteId" name="deleteId" readonly="true">
                            <label for="deleteId">编号</label>
                            <span class="help-block">唯一编号</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="deleteName" name="deleteName" readonly="true">
                            <label for="deleteName">角色名称</label>
                            <span class="help-block">将要删除的角色名称</span>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-danger"
                               value="删 除"/>
                        <input type="reset" class="btn" data-dismiss="modal"
                               value="关 闭"/>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>