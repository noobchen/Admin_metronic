<%--
  Created by IntelliJ IDEA.
  User: cyc
  Date: 2016/10/26
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../pageLevel/selectScript.jsp"%>

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
        TableDatatables.init($("#operator_table"));

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
                operatorName:{
                    required:true,
                    minlength:1,
                    maxlength:100
                },
                operatorCompany:{
                    required:true,
                    minlength:1,
                    maxlength:100
                },
                operatorEmail:{
                    required:false,
                    maxlength:100
                },
                operatorPhone:{
                    required:false,
                    maxlength:20
                },
                roles:{
                    required:true,
                    min:0
                },
                accountType:{
                    required:true,
                    min:0
                },
                accountTypeId:{
                    required:function () {
                        return $("#accountType").val() != "0";
                    },
                    digits:true,
                    min:0,
                    max:100000000
                },
                remark:{
                    required:false,
                    maxlength:1024
                }
            },
            messages:{
                operatorName:{
                    required:"请输入用户名称",
                    minlength:"用户名称必须包含1个字符",
                    maxlength:"用户名称最多包含50个字符"
                },
                operatorCompany:{
                    required:"请输入公司名称",
                    minlength:"公司名称必须包含1个字符",
                    maxlength:"公司名称最多包含100个字符"
                },
                operatorEmail:{
                    maxlength:"邮箱地址最多包含100个字符"
                },
                operatorPhone:{
                    maxlength:"联系电话最多包含20个字符"
                },
                roles:{
                    required:"请选择用户角色",
                    min:"请选择用户角色"
                },
                accountType:{
                    required:"请选择账户类型",
                    min:"请选择账户类型"
                },
                accountTypeId:{
                    required:"请输入账户类型编号",
                    digits:"账户类型编号必须为整数",
                    min:"账户类型编号最小值为0",
                    max:"账户类型编号最大值为100000000"
                },
                remark:{
                    maxlength:"备注最多包含1024个字符"
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
                editOperatorName:{
                    required:true,
                    minlength:1,
                    maxlength:100
                },
                editOperatorCompany:{
                    required:true,
                    minlength:1,
                    maxlength:100
                },
                editOperatorEmail:{
                    required:false,
                    maxlength:100
                },
                editOperatorPhone:{
                    required:false,
                    maxlength:20
                },
                editPassword:{
                    required:false,
                    minlength:5,
                    maxlength:50
                },
                editRoles:{
                    required:true,
                    min:0
                },
                editAccountType:{
                    required:true,
                    min:0
                },
                editAccountTypeId:{
                    required:function () {
                        return $("#editAccountType").val() != "0";
                    },
                    digits:true,
                    min:0,
                    max:100000000
                },
                editRemark:{
                    required:false,
                    maxlength:1024
                },
                editStatus:{
                    required:true,
                    min:0
                }
            },
            messages:{
                editOperatorName:{
                    required:"请输入用户名称",
                    minlength:"用户名称必须包含1个字符",
                    maxlength:"用户名称最多包含50个字符"
                },
                editOperatorCompany:{
                    required:"请输入公司名称",
                    minlength:"公司名称必须包含1个字符",
                    maxlength:"公司名称最多包含100个字符"
                },
                editOperatorEmail:{
                    maxlength:"邮箱地址最多包含100个字符"
                },
                editOperatorPhone:{
                    maxlength:"联系电话最多包含20个字符"
                },
                editPassword:{
                    minlength:"用户密码必须包含5个字符",
                    maxlength:"用户密码最多包含50个字符"
                },
                editRoles:{
                    required:"请选择用户角色",
                    min:"请选择用户角色"
                },
                editAccountType:{
                    required:"请选择账户类型",
                    min:"请选择账户类型"
                },
                editAccountTypeId:{
                    required:"请输入账户类型编号",
                    digits:"账户类型编号必须为整数",
                    min:"账户类型编号最小值为0",
                    max:"账户类型编号最大值为100000000"
                },
                editRemark:{
                    maxlength:"备注最多包含1024个字符"
                },
                editStatus:{
                    required:"请选择用户状态",
                    min:"请选择用户状态"
                }
            }
        });


        $("#accountType").change(function(){
            if ($("#accountType").val() == "0") {
                document.getElementById("accountTypeIdDiv").style.display = 'none';
            } else {
                document.getElementById("accountTypeIdDiv").style.display = 'block';
            }
        });

        $("#editAccountType").change(function(){
            if ($("#editAccountType").val() == "0") {
                document.getElementById("editAccountTypeIdDiv").style.display = 'none';
            } else {
                document.getElementById("editAccountTypeIdDiv").style.display = 'block';
            }
        });


        $('a[name="editButton"]').click(function () {
            $("#editAccountType").change();

            //JQuery+Metronic前端框架checkbox样式不渲染
            //http://blog.sina.com.cn/s/blog_4d1085940102vxy3.html
            App.init();
        });


        $("#operator_table").find("tr").hover(function () {
            $("#deleteId").val($(this).children("td").eq(0).html());
            $("#deleteName").val($(this).children("td").eq(1).html());
            $("#editId").val($(this).children("td").eq(0).html());
            $("#editOperatorName").val($(this).children("td").eq(1).html());
            $("#editOperatorCompany").val($(this).children("td").eq(6).html());
            $("#editOperatorEmail").val($(this).children("td").eq(7).html());
            $("#editOperatorPhone").val($(this).children("td").eq(8).html());
            $("#editRemark").val($(this).children("td").eq(9).html());

            $("#editStatus").val($(this).children("td").eq(13).html());
            $("#editAccountType").val($(this).children("td").eq(14).html());
            $("#editAccountTypeId").val($(this).children("td").eq(5).html());

            var content = $(this).children("td").eq(12).html();


            if (null != content) {
                var operateTypes = new Array();
                operateTypes = content.split(",");
                for (i = 0; i < operateTypes.length; i++) {
                    operateTypes[i] = jQuery.trim(operateTypes[i]);
                }

                //mult select 设置选中值
                $("#editRoles").selectpicker('val', operateTypes);
            }
        })
    });
</script>
