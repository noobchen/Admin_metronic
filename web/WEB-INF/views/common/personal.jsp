<%--
  Created by IntelliJ IDEA.
  User: cyc
  Date: 2016/10/27
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- BEGIN PAGE TITLE-->
<h3 class="page-title">用户信息
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

                            </div>
                            <div class="col-md-6">

                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover table-bordered" id="personal_table">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户名称</th>
                            <th>登陆账号</th>
                            <th>角色</th>
                            <th>公司名称</th>
                            <th>电子邮箱</th>
                            <th>电话号码</th>
                            <th>备注</th>
                            <th>状态</th>
                            <th>建立时间</th>
                            <th style="display: none;">角色</th>
                            <th style="display: none;">状态</th>
                            <th> 修改</th>
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



<div id="edit" class="modal fade" tabindex="-1">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

        <h3>修 改</h3>
    </div>
    <div class="portlet-body form">
        <div class="form-body">
            <form action="personalEdit.do" method="post" id="editForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editId" name="editId" readonly="true">
                            <label for="editId">编号</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editOperatorName" name="editOperatorName" readonly="true">
                            <label for="editOperatorName">用户名称</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editOperatorCompany" name="editOperatorCompany" readonly="true">
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
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editRemark" name="editRemark">
                            <label for="editRemark">备注</label>
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


