<%--
  Created by IntelliJ IDEA.
  User: cyc
  Date: 2016/10/26
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- BEGIN PAGE TITLE-->
<h3 class="page-title">用户管理
    <small> 1：创建用户账号密码；</small>
    <small> 2：配置用户角色；</small>
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
    明 细
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
                    <table class="table table-striped table-hover table-bordered" id="operator_table">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户名称</th>
                            <th>登陆账号</th>
                            <th>角色</th>
                            <th>账户类型</th>
                            <th style="display: none">账户类型编号</th>
                            <th>公司名称</th>
                            <th>电子邮箱</th>
                            <th>电话号码</th>
                            <th>备注</th>
                            <th>状态</th>
                            <th>建立时间</th>
                            <th style="display: none;">角色</th>
                            <th style="display: none;">状态</th>
                            <th style="display: none;">账户类型</th>
                            <th> 修改</th>
                            <th> 删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${null != pageInfo.result}">
                            <c:forEach var="item" items="${pageInfo.result}" step="1">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.operatorName}</td>
                                    <td>${item.account}</td>
                                    <td>
                                        <c:if test="${null != item.roles}">
                                            <c:forEach var="role" items="${item.roles}" step="1" varStatus="stat">
                                                ${role.name}<c:if test="${!stat.last}">,</c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${item.accountType == '0'}">
                                            管理员
                                        </c:if>
                                        <c:if test="${item.accountType == '1'}">
                                            CP
                                        </c:if>
                                        <c:if test="${item.accountType == '2'}">
                                            SP
                                        </c:if>
                                        <c:if test="${item.accountType == '3'}">
                                            渠道
                                        </c:if>
                                        <c:if test="${item.accountType == '4'}">
                                            下游通道商
                                        </c:if>
                                    </td>
                                    <td style="display: none">${item.accountTypeId}</td>
                                    <td>${item.operatorCompany}</td>
                                    <td>${item.operatorEmail}</td>
                                    <td>${item.operatorPhone}</td>
                                    <td>${item.remark}</td>
                                    <td>
                                        <c:if test="${item.status=='0'}">
                                            正常
                                        </c:if>
                                        <c:if test="${item.status=='1'}">
                                            关闭
                                        </c:if>
                                    </td>
                                    <td>${item.createTime}</td>
                                    <td style="display: none;">
                                        <c:if test="${null != item.roles}">
                                            <c:forEach var="role" items="${item.roles}" step="1" varStatus="stat">
                                                ${role.id}<c:if test="${!stat.last}">,</c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <td style="display: none;">${item.status}</td>
                                    <td style="display: none;">${item.accountType}</td>
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
    <div class="portlet-body form" >
        <div class="form-body">
            <form action="operatorAdd.do" method="post" id="addForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="operatorName" name="operatorName">
                            <label for="operatorName">用户名称</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="operatorCompany" name="operatorCompany">
                            <label for="operatorCompany">公司名称</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="operatorEmail" name="operatorEmail">
                            <label for="operatorEmail">电子邮箱</label>
                            <span class="help-block">没有，可为空</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="operatorPhone" name="operatorPhone">
                            <label for="operatorPhone">联系电话</label>
                            <span class="help-block">没有，可为空</span>
                        </div>

                        <div class="form-group form-md-line-input">
                            <select class="bs-select form-control"  id="roles" name="roles" multiple >
                                <c:if test="${null != roleList}">
                                    <c:forEach var="item" items="${roleList}" step="1">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <label for="roles">用户角色，可多选</label>
                            <span class="help-block">角色决定用户访问资源权限</span>
                        </div>

                        <div class="form-group form-md-line-input form-md-floating-label has-info">
                            <select class="form-control edited" id="accountType" name="accountType" >
                                <option value="">请选择账户类型</option>
                                <option value="0">管理员</option>
                                <option value="1">CP</option>
                                <option value="2">SP</option>
                                <option value="3">渠道</option>
                                <option value="4">下游通道商</option>
                            </select>
                            <span class="help-block">账户类型</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label" id="accountTypeIdDiv" style="display: none">
                            <input type="text" class="form-control" id="accountTypeId" name="accountTypeId">
                            <label for="accountTypeId">账户类型编号</label>
                            <span class="help-block">CP：1；SP：2；渠道：3；下游通道商：4；</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="remark" name="remark">
                            <label for="remark">备注</label>
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
            <form action="operatorEdit.do" method="post" id="editForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editId" name="editId" readonly="true">
                            <label for="editId">编号</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editOperatorName" name="editOperatorName">
                            <label for="editOperatorName">用户名称</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editOperatorCompany" name="editOperatorCompany">
                            <label for="editOperatorCompany">公司名称</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editOperatorEmail" name="editOperatorEmail">
                            <label for="editOperatorEmail">电子邮箱</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editOperatorPhone" name="editOperatorPhone">
                            <label for="editOperatorPhone">联系电话</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editPassword" name="editPassword">
                            <label for="editPassword">用户密码</label>
                            <span class="help-block">输入新密码</span>
                        </div>
                        <div class="form-group form-md-line-input">
                            <select class="bs-select form-control"  id="editRoles" name="editRoles" multiple >
                                <c:if test="${null != roleList}">
                                    <c:forEach var="item" items="${roleList}" step="1">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <label for="editRoles">用户角色，可多选</label>
                            <span class="help-block">角色决定用户访问资源权限</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label has-info">
                            <select class="form-control edited" id="editAccountType" name="editAccountType" >
                                <option value="">请选择账户类型</option>
                                <option value="0">管理员</option>
                                <option value="1">CP</option>
                                <option value="2">SP</option>
                                <option value="3">渠道</option>
                                <option value="4">下游通道商</option>
                                <span class="help-block">账户类型</span>
                            </select>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label" id="editAccountTypeIdDiv" style="display: none">
                            <input type="text" class="form-control" id="editAccountTypeId" name="editAccountTypeId">
                            <label for="accountTypeId">账户类型编号</label>
                            <span class="help-block">CP：1；SP：2；渠道：3；下游通道商：4；</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editRemark" name="editRemark">
                            <label for="editRemark">备注</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label has-info">
                            <select class="form-control edited" id="editStatus" name="editStatus" >
                                <option value="0">正常</option>
                                <option value="1">关闭</option>
                            </select>
                            <label for="editStatus">关闭状态，账号将停用</label>
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
            <form action="operatorDelete.do" method="post" id="deleteForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="deleteId" name="deleteId" readonly="true">
                            <label for="deleteId">编号</label>
                            <span class="help-block">唯一编号</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="deleteName" name="deleteName" readonly="true">
                            <label for="deleteName">用户名称</label>
                            <span class="help-block">将要删除的用户名称</span>
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