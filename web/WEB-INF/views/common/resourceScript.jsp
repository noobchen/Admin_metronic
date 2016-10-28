<%--
  Created by IntelliJ IDEA.
  User: cyc
  Date: 2016/10/24
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function (form) {
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
        TableDatatables.init($("#resource_table"));

        // validate form on keyup and submit
        $("#addForm").validate({
            errorElement: 'span',
            errorClass: 'help-inline',
            highlight: function (element, errorClass) {
                $(element).parent().parent().removeClass('success');
                $(element).parent().parent().addClass('error');
            },
            unhighlight: function (element, errorClass) {
                $(element).parent().parent().removeClass('error');
                $(element).parent().parent().addClass('success');
            },
            rules: {
                resourceName: {
                    required: true,
                    minlength: 1,
                    maxlength: 50
                },
                baseUrl: {
                    required: false,
                    maxlength: 200
                },
                resourceUrl: {
                    required: true,
                    minlength: 1,
                    maxlength: 200
                },
                type: {
                    required: true,
                    min: 1
                },
                parentId: {
                    required: function () {
                        return $("#type").val() == "2";
                    },
                    min: 0
                },
                operateType: {
                    required: function () {
                        return $("#type").val() == "2";
                    },
                    minlength: 1
                },
                index: {
                    required: true,
                    digits: true,
                    min: 0,
                    max: 100
                }
            },
            messages: {
                resourceName: {
                    required: "请输入资源名称",
                    minlength: "资源名称必须包含1个字符",
                    maxlength: "资源名称最多包含50个字符"
                },
                baseUrl: {
                    maxlength: "主路径最多包含200个字符"
                },
                resourceUrl: {
                    required: "请输入URL地址",
                    minlength: "URL必须包含1个字符",
                    maxlength: "URL最多包含200个字符"
                },
                type: {
                    required: "请选择类型",
                    min: "请选择类型"
                },
                parentId: {
                    required: "请选择所属模块",
                    min: "请选择所属模块"
                },
                operateType: {
                    required: "<font color='#b94a48'>请选择权限</font>",
                    minlength: "<font color='#b94a48'>请选择权限</font>"
                },
                index: {
                    required: "请输入排列序号",
                    digits: "排列序号必须为整数",
                    min: "排列序号最少为0",
                    max: "排列序号最大为100"
                }
            },
            errorPlacement: function (error, element) {
                if (element.is(':radio') || element.is(':checkbox')) {
                    var eid = element.attr('name');
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });


        $("#editForm").validate({
            errorElement: 'span',
            errorClass: 'help-inline',
            highlight: function (element, errorClass) {
                $(element).parent().parent().removeClass('success');
                $(element).parent().parent().addClass('error');
            },
            unhighlight: function (element, errorClass) {
                $(element).parent().parent().removeClass('error');
                $(element).parent().parent().addClass('success');
            },
            rules: {
                editResourceName: {
                    required: true,
                    minlength: 1,
                    maxlength: 50
                },
                editBaseUrl: {
                    required: false,
                    maxlength: 200
                },
                editResourceUrl: {
                    required: true,
                    minlength: 1,
                    maxlength: 200
                },
                editType: {
                    required: true,
                    min: 1
                },
                editParentId: {
                    required: function () {
                        return $("#editType").val() == "2";
                    },
                    min: 0
                },
                editOperateType: {
                    required: function () {
                        return $("#editType").val() == "2";
                    },
                    minlength: 1
                },
                editIndex: {
                    required: true,
                    digits: true,
                    min: 0,
                    max: 100
                }
            },
            messages: {
                editResourceName: {
                    required: "请输入资源名称",
                    minlength: "资源名称必须包含1个字符",
                    maxlength: "资源名称最多包含50个字符"
                },
                editBaseUrl: {
                    maxlength: "主路径最多包含200个字符"
                },
                editResourceUrl: {
                    required: "请输入URL地址",
                    minlength: "URL必须包含1个字符",
                    maxlength: "URL必须包含200个字符"
                },
                editType: {
                    required: "请选择类型",
                    min: "请选择类型"
                },
                editParentId: {
                    required: "请选择所属模块",
                    min: "请选择所属模块"
                },
                editOperateType: {
                    required: "<font color='#b94a48'>请选择权限</font>",
                    minlength: "<font color='#b94a48'>请选择权限</font>"
                },
                editIndex: {
                    required: "请输入排列序号",
                    digits: "排列序号必须为整数",
                    min: "排列序号最少为0",
                    max: "排列序号最大为100"
                }
            },
            errorPlacement: function (error, element) {
                if (element.is(':radio') || element.is(':checkbox')) {
                    var eid = element.attr('name');
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });

        $("#type").change(function () {
            if ($("#type").val() == "1") {
                document.getElementById("parentIdDiv").style.display = 'none';
                document.getElementById("operateTypeDiv").style.display = 'none';
                document.getElementById("baseUrlDiv").style.display = 'block';

            } else {
                document.getElementById("parentIdDiv").style.display = 'block';
                document.getElementById("operateTypeDiv").style.display = 'block';
                document.getElementById("baseUrlDiv").style.display = 'none';
            }

        });

        $("#editType").change(function () {
            if ($("#editType").val() == "1") {
                document.getElementById("editParentIdDiv").style.display = 'none';
                document.getElementById("editOperateTypeDiv").style.display = 'none';
                document.getElementById("editBaseUrlDiv").style.display = 'block';
            } else {
                document.getElementById("editParentIdDiv").style.display = 'block';
                document.getElementById("editOperateTypeDiv").style.display = 'block';
                document.getElementById("editBaseUrlDiv").style.display = 'none';
            }

        });

        $("#resource_table").on('click', 'a[name="editButton"]', function (e) {
            var tds = $(this).parents("tr").children("td");

            $("#editId").val(tds.eq(0).html());
            $("#editResourceName").val(tds.eq(1).html());
            $("#editBaseUrl").val(tds.eq(4).html());
            $("#editResourceUrl").val(tds.eq(5).html());
            $("#editType").val(tds.eq(2).html());
            $("#editParentId").val(tds.eq(7).html());
            $("#editIndex").val(tds.eq(9).html());

            $("#editType").change();

            var content = tds.eq(6).html();

            if (null != content) {
                var operateTypes = new Array();
                operateTypes = content.split(",");

                var tem = new Array();


                for (i = 0; i < operateTypes.length; i++) {
                    if (jQuery.trim(operateTypes[i]) == "QUERY") {
                        tem[0] = true;
                    }
                    if (jQuery.trim(operateTypes[i]) == "ADD") {
                        tem[1] = true;
                    }
                    if (jQuery.trim(operateTypes[i]) == "EDIT") {
                        tem[2] = true;
                    }
                    if (jQuery.trim(operateTypes[i]) == "DELETE") {
                        tem[3] = true;
                    }
                }
                if (tem[0] == true) {
                    $("#editOperateType_1").prop("checked", true);
                } else {
                    $("#editOperateType_1").prop("checked", false);
                }

                if (tem[1] == true) {
                    $("#editOperateType_2").prop("checked", true);
                } else {
                    $("#editOperateType_2").prop("checked", false);
                }

                if (tem[2] == true) {
                    $("#editOperateType_3").prop("checked", true);
                } else {
                    $("#editOperateType_3").prop("checked", false);
                }

                if (tem[3] == true) {
                    $("#editOperateType_4").prop("checked", true);
                } else {
                    $("#editOperateType_4").prop("checked", false);
                }
                //设置checkbox checked 无效
                //http://stackoverflow.com/questions/37267793/unable-to-check-uncheck-a-checkbox-using-jquery-in-metronic-admin-theme
                $.uniform.update();
            }

            //JQuery+Metronic前端框架checkbox样式不渲染
            //http://blog.sina.com.cn/s/blog_4d1085940102vxy3.html
            App.init();
        });

        $("#resource_table").on('click', 'a[name="delButton"]', function (e) {
            var tds = $(this).parents("tr").children("td");

            $("#deleteId").val(tds.eq(0).html());
            $("#deleteName").val(tds.eq(1).html());
        });

    });
</script>
