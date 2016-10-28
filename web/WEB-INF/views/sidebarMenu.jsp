<%--
  Created by IntelliJ IDEA.
  User: cyc
  Date: 2016/10/23
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
    <!-- BEGIN SIDEBAR -->
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
        <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
        <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
        <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
        <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
        <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
        <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true"
            data-slide-speed="200" style="padding-top: 20px">
            <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
            <li class="sidebar-toggler-wrapper hide">
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler"></div>
                <!-- END SIDEBAR TOGGLER BUTTON -->
            </li>
            <c:if test="${null != module}">
                <c:forEach var="item" items="${module}" step="1">
                    <li class="nav-item  <c:if test="${null != moduleId and moduleId ==item.id}">active open</c:if>">
                        <a href="javascript:;" class="nav-link nav-toggle">
                            <i class="icon-diamond"></i>
                            <span class="title">${item.resourceName}</span>
                            <span class="arrow"></span>
                        </a>
                        <c:if test="${null != item.subMenus}">
                            <ul class="sub-menu">
                                <c:forEach var="subItem" items="${item.subMenus}" step="1">
                                    <c:if test="${null == subItem.parentResource.baseUrl or subItem.parentResource.baseUrl ==''}">
                                        <li class="nav-item  <c:if test="${null != menuId and menuId ==subItem.id}">active open</c:if>">
                                            <a href="${subItem.url}?module=${item.id}&menu=${subItem.id}" class="nav-link ">
                                                <span class="title">${subItem.resourceName}</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${null != subItem.parentResource.baseUrl and subItem.parentResource.baseUrl !=''}">
                                        <li class="nav-item  <c:if test="${null != menuId and menuId ==subItem.id}">active open</c:if>">
                                            <a href="${'request.do?rurl='}${subItem.encryptionUrlString}" class="nav-link ">
                                                <span class="title">${subItem.resourceName}</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <!-- END SIDEBAR MENU -->
        <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
</div>
<!-- END SIDEBAR -->
