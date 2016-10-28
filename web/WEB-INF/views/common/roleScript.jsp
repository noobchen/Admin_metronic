<%--
  Created by IntelliJ IDEA.
  User: cyc
  Date: 2016/10/26
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler:function (form) {
            if (form.id == 'queryForm') {
                queryForm.submit();
            }
            else if (form.id == 'addForm') {
                addForm.submit();
            }
            else if (form.id == 'editForm') {
                editForm.submit();
            }
            else {
                deleteForm.submit();
            }
        }
    });

    $().ready(function () {
        TableDatatables.init($("#role_table"));
        // validate form on keyup and submit
        $("#addForm").validate({
            errorElement:'span',
            errorClass:'help-inline',
            highlight:function (element, errorClass) {
                $(element).parent().parent().removeClass('success');
                $(element).parent().parent().addClass('error');
            },
            unhighlight:function (element, errorClass) {
                $(element).parent().parent().removeClass('error');
                $(element).parent().parent().addClass('success');
            },
            rules:{
                roleName:{
                    required:true,
                    minlength:1,
                    maxlength:50
                },
                moduleName:{
                    required:true,
                    min:0
                }
            },
            messages:{
                roleName:{
                    required:"请输入角色名称",
                    minlength:"角色名称必须包含1个字符",
                    maxlength:"角色名称最多包含50个字符"
                },
                moduleName:{
                    required:"请选择管理模块",
                    min:"请选择管理模块"
                }
            }
        });

        $("#editForm").validate({
            errorElement:'span',
            errorClass:'help-inline',
            highlight:function (element, errorClass) {
                $(element).parent().parent().removeClass('success');
                $(element).parent().parent().addClass('error');
            },
            unhighlight:function (element, errorClass) {
                $(element).parent().parent().removeClass('error');
                $(element).parent().parent().addClass('success');
            },
            rules:{
                editRoleName:{
                    required:true,
                    minlength:1,
                    maxlength:50
                }
            },
            messages:{
                editRoleName:{
                    required:"请输入角色名称",
                    minlength:"角色名称必须包含1个字符",
                    maxlength:"角色名称最多包含50个字符"
                }
            }
        });

        $("#editForm").validate({
            errorElement:'span',
            errorClass:'help-inline',
            highlight:function (element, errorClass) {
                $(element).parent().parent().removeClass('success');
                $(element).parent().parent().addClass('error');
            },
            unhighlight:function (element, errorClass) {
                $(element).parent().parent().removeClass('error');
                $(element).parent().parent().addClass('success');
            },
            rules:{
                editRoleName:{
                    required:true,
                    minlength:1,
                    maxlength:50
                }
            },
            messages:{
                editRoleName:{
                    required:"请输入角色名称",
                    minlength:"角色名称必须包含1个字符",
                    maxlength:"角色名称最多包含50个字符"
                }
            }
        });

        $("#moduleName").change(function () {
            if ($("#moduleName").val() == '') {
                //去除下面的选项
                $("#menuNameBody").empty();
            } else {
                loadMenuData($("#menuNameBody"),$("#moduleName").val(),"false");
            }
        });

        $('a[name="editButton"]').click(function () {
            //去除下面的选项
            $("#editMenuNameBody").empty();
            loadMenuData($("#editMenuNameBody"),$("#editModuleId").val(),"true");

            //JQuery+Metronic前端框架checkbox样式不渲染
            //http://blog.sina.com.cn/s/blog_4d1085940102vxy3.html
            App.init();
        });

        $("#role_table").find("tr").hover(function () {
            $("#deleteId").val($(this).children("td").eq(0).html());
            $("#deleteName").val($(this).children("td").eq(1).html());
            $("#editId").val($(this).children("td").eq(0).html());
            $("#editRoleName").val($(this).children("td").eq(1).html());

            $("#powerId").val($(this).children("td").eq(0).html());
            $("#powerName").val($(this).children("td").eq(1).html());

            $("#editModuleName").val(jQuery.trim($(this).children("td").eq(3).html()));
            $("#editModuleId").val(jQuery.trim($(this).children("td").eq(4).html()));
        })

        function loadSelectMenuData(roleId){
            function update(respdata) {
                for (var i = 0; i < respdata.length; i++) {
                    for (var j = 0; j < respdata[i].operates.length; j++) {
                        if (respdata[i].operates[j].operateType == "QUERY") {
                            var name = "#operateType_query"+ respdata[i].id;
                            $(name).attr("checked", true);
                        }
                        if (respdata[i].operates[j].operateType == "ADD") {
                            var name = "#operateType_add"+ respdata[i].id;
                            $(name).attr("checked", true);
                        }
                        if (respdata[i].operates[j].operateType == "EDIT") {
                            var name = "#operateType_edit"+ respdata[i].id;
                            $(name).attr("checked", true);
                        }
                        if (respdata[i].operates[j].operateType == "DELETE") {
                            var name = "#operateType_delete"+ respdata[i].id;
                            $(name).attr("checked", true);
                        }
                    }
                }
            }
            // ajax
            ajaxSend("getSelectMenuInfo.do", {'roleId':roleId}, update);
        }

        function loadMenuData(compent,moduleId,selectFlag) {
            function update(respdata) {
                compent.empty();
                for (var i = 0; i < respdata.length; i++) {
                    var s = "";
                    var flag = new Array();
                    for (var j = 0; j < respdata[i].operates.length; j++) {
                        if (respdata[i].operates[j].operateType == "QUERY") {
                            flag[0] = true;
                        }
                        if (respdata[i].operates[j].operateType == "ADD") {
                            flag[1] = true;
                        }
                        if (respdata[i].operates[j].operateType == "EDIT") {
                            flag[2] = true;
                        }
                        if (respdata[i].operates[j].operateType == "DELETE") {
                            flag[3] = true;
                        }
                    }

                    if (flag[0] == true) {
                        s += "<input type=\"checkbox\" id=\"operateType_query" + respdata[i].id +"\" name=\"query\" value='" + respdata[i].id + "'>查询";
                    } else {
                        s += "<input type=\"checkbox\" id=\"operateType_query" + respdata[i].id +"\" name=\"query\" value='' disabled='disabled'>查询";
                    }

                    if (flag[1] == true) {
                        s += "<input type=\"checkbox\" id=\"operateType_add" + respdata[i].id +"\" name=\"add\" value='" + respdata[i].id + "'>新增";
                    } else {
                        s += "<input type=\"checkbox\" id=\"operateType_add" + respdata[i].id +"\" name=\"add\" value=value='' disabled='disabled'>新增";
                    }

                    if (flag[2] == true) {
                        s += "<input type=\"checkbox\" id=\"operateType_edit" + respdata[i].id +"\" name=\"edit\" value='" + respdata[i].id + "'>修改";
                    } else {
                        s += "<input type=\"checkbox\" id=\"operateType_edit" + respdata[i].id +"\" name=\"edit\" value='' disabled='disabled'>修改";
                    }

                    if (flag[3] == true) {
                        s += "<input type=\"checkbox\" id=\"operateType_delete" + respdata[i].id +"\" name=\"delete\" value='" + respdata[i].id + "'>删除";
                    } else {
                        s += "<input type=\"checkbox\" id=\"operateType_delete" + respdata[i].id +"\" name=\"delete\" value='' disabled='disabled'>删除";
                    }

                    compent.append("<tr>"
                            + "<td>" + respdata[i].resourceName + "<input type=\"hidden\" name='menuId' value='" + respdata[i].id + "'>" + "</td>"
                            + "<td>"
                            + s
                            + "</td>"
                            + "</tr>");
                }

                if(selectFlag == "true"){
                    loadSelectMenuData($("#editId").val());
                }
            }

            // ajax
            ajaxSend("getMenuInfo.do", {'moduleId':moduleId}, update);
        }

        function ajaxSend(url, data, callback) {
            $.ajax({
                type:"POST",
                url:url,
                data:data,
                timeout:5000,
                success:function (msg) {
                    callback(msg);
                }
            });
        }
    });
</script>
