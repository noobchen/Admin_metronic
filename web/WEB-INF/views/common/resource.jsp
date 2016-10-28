<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!-- BEGIN PAGE TITLE-->
<h3 class="page-title">站点资源管理
    <small> 1：用户访问权限管理；</small>
    <small> 2：菜单url地址管理；</small>
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
                    <table class="table table-striped table-hover table-bordered" id="resource_table">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>资源名称</th>
                            <th style="display: none">类型</th>
                            <th>类型</th>
                            <th>主路径</th>
                            <th>URL</th>
                            <th>权限</th>
                            <th style="display: none">所属模块</th>
                            <th>所属模块</th>
                            <th>排序</th>
                            <th>建立时间</th>
                            <th> 修改</th>
                            <th> 删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${null != pageInfo.result}">
                            <c:forEach var="item" items="${pageInfo.result}" step="1">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.resourceName}</td>
                                    <td style="display: none">${item.type}</td>
                                    <td>
                                        <c:if test="${item.type == 1}">
                                            模块
                                        </c:if>
                                        <c:if test="${item.type == 2}">
                                            菜单
                                        </c:if>
                                    </td>
                                    <td>${item.baseUrl}</td>
                                    <td>${item.url}</td>
                                    <td>
                                        <c:if test="${null != item.operates}">
                                            <c:forEach var="operate" items="${item.operates}" step="1"
                                                       varStatus="stat">
                                                ${operate.operateType}<c:if test="${!stat.last}">,</c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <td style="display: none">${item.parentResource.id}</td>
                                    <td>${item.parentResource.resourceName}</td>
                                    <td>${item.index}</td>
                                    <td>${item.createTime}</td>
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
            <form action="resourceAdd.do" method="post" id="addForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="resourceName" name="resourceName">
                            <label for="resourceName">资源名称</label>
                            <span class="help-block">模块或菜单名称</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label has-info">
                            <select class="form-control edited" id="type" name="type">
                                <option value="1">模块</option>
                                <option value="2">菜单</option>
                            </select>
                            <label for="type">类 型</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label" id="baseUrlDiv">
                            <input type="text" class="form-control" id="baseUrl" name="baseUrl">
                            <label for="baseUrl">主路径</label>
                            <span class="help-block">为空表示本应用</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="resourceUrl" name="resourceUrl">
                            <label for="resourceUrl">URL</label>
                            <span class="help-block">菜单或模块URL</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label has-info" id="parentIdDiv"
                             style="display: none">
                            <select class="form-control edited" id="parentId" name="parentId">
                                <option value="">请选择所属模块</option>
                                <c:if test="${null != resourceInfos}">
                                    <c:forEach var="item" items="${resourceInfos}" step="1">
                                        <option value="${item.id}">${item.resourceName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <label for="parentId">所属模块</label>
                        </div>
                        <div class="form-group form-md-checkboxes" id="operateTypeDiv" style="display: none;">
                            <label>操作权限</label>
                            <div class="md-checkbox-inline">
                                <div class="md-checkbox">
                                    <input type="checkbox" id="operateType_1" class="md-check" value="QUERY"
                                           name="operateType">
                                    <label for="operateType_1">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 查询 </label>
                                </div>
                                <div class="md-checkbox">
                                    <input type="checkbox" id="operateType_2" class="md-check" value="ADD"
                                           name="operateType">
                                    <label for="operateType_2">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 新增 </label>
                                </div>
                                <div class="md-checkbox">
                                    <input type="checkbox" id="operateType_3" class="md-check" value="EDIT"
                                           name="operateType">
                                    <label for="operateType_3">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 修改 </label>
                                </div>
                                <div class="md-checkbox">
                                    <input type="checkbox" id="operateType_4" class="md-check" value="DELETE"
                                           name="operateType">
                                    <label for="operateType_4">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 删除 </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="index" name="index">
                            <label for="index">排列序号</label>
                            <span class="help-block">数字越小排列越前</span>
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
            <form action="resourceEdit.do" method="post" id="editForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editId" name="editId" readonly="true">
                            <label for="editId">编号</label>
                            <span class="help-block">模块或菜单名称</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editResourceName" name="editResourceName">
                            <label for="editResourceName">资源名称</label>
                            <span class="help-block">模块或菜单名称</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label" id="editBaseUrlDiv"
                             style="display: none;">
                            <input type="text" class="form-control" id="editBaseUrl" name="editBaseUrl">
                            <label for="editBaseUrl">主路径</label>
                            <span class="help-block">为空表示本应用</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editResourceUrl" name="editResourceUrl">
                            <label for="editResourceUrl">URL</label>
                            <span class="help-block">菜单或模块URL</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label has-info">
                            <select class="form-control edited" id="editType" name="editType" disabled="disabled">
                                <option value="1">模块</option>
                                <option value="2">菜单</option>
                            </select>
                            <label for="editType">类 型</label>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label has-info" id="editParentIdDiv"
                             style="display: none">
                            <select class="form-control edited" id="editParentId" name="editParentId">
                                <option value="">请选择所属模块</option>
                                <c:if test="${null != resourceInfos}">
                                    <c:forEach var="item" items="${resourceInfos}" step="1">
                                        <option value="${item.id}">${item.resourceName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <label for="editParentId">所属模块</label>
                        </div>
                        <div class="form-group form-md-checkboxes" id="editOperateTypeDiv" style="display: none;">
                            <label>操作权限</label>
                            <div class="md-checkbox-inline">
                                <div class="md-checkbox">
                                    <input type="checkbox" id="editOperateType_1" class="md-check" value="QUERY"
                                           name="editOperateType">
                                    <label for="editOperateType_1">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 查询 </label>
                                </div>
                                <div class="md-checkbox">
                                    <input type="checkbox" id="editOperateType_2" class="md-check" value="ADD"
                                           name="editOperateType">
                                    <label for="editOperateType_2">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 新增 </label>
                                </div>
                                <div class="md-checkbox">
                                    <input type="checkbox" id="editOperateType_3" class="md-check" value="EDIT"
                                           name="editOperateType">
                                    <label for="editOperateType_3">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 修改 </label>
                                </div>
                                <div class="md-checkbox">
                                    <input type="checkbox" id="editOperateType_4" class="md-check" value="DELETE"
                                           name="editOperateType">
                                    <label for="editOperateType_4">
                                        <span></span>
                                        <span class="check"></span>
                                        <span class="box"></span> 删除 </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="editIndex" name="editIndex">
                            <label for="editIndex">排列序号</label>
                            <span class="help-block">数字越小排列越前</span>
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
            <form action="resourceDelete.do" method="post" id="deleteForm">
                <fieldset>
                    <div class="modal-body">
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="deleteId" name="deleteId" readonly="true">
                            <label for="deleteId">编号</label>
                            <span class="help-block">唯一编号</span>
                        </div>
                        <div class="form-group form-md-line-input form-md-floating-label">
                            <input type="text" class="form-control" id="deleteName" name="deleteName" readonly="true">
                            <label for="deleteName">资源名称</label>
                            <span class="help-block">将要删除的资源名称</span>
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
