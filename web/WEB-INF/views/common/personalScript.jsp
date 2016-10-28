<%--
  Created by IntelliJ IDEA.
  User: cyc
  Date: 2016/10/27
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler:function () {
            editForm.submit();
        }
    });

    $().ready(function () {
        TableDatatables.init($("#personal_table"));
        // validate form on keyup and submit
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
                editRemark:{
                    required:false,
                    maxlength:1024
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
                editRemark:{
                    maxlength:"备注最多包含1024个字符"
                }
            }
        });


        $("#personal_table").find("tr").hover(function () {
            $("#editId").val($(this).children("td").eq(0).html());
            $("#editOperatorName").val($(this).children("td").eq(1).html());
            $("#editOperatorCompany").val($(this).children("td").eq(4).html());
            $("#editOperatorEmail").val($(this).children("td").eq(5).html());
            $("#editOperatorPhone").val($(this).children("td").eq(6).html());
            $("#editRemark").val($(this).children("td").eq(7).html());
        })
    });
</script>
