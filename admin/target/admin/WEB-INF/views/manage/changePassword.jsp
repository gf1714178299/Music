<%--
  Created by IntelliJ IDEA.
  User: D丶Cheng
  Date: 2017/10/25
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="required" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="jsFile.jsp"%>
    <%--管理页面通用样式--%>
    <link rel="stylesheet" type="text/css" href="../H-ui/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../H-ui/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <%--管理页面通用样式--%>
    <title></title>
</head>
<body>
<article class="page-container">
    <div class="form form-horizontal">
        <form class="form form-horizontal" id="form-admin">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>原始密码：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="password" class="input-text" placeholder="" id="oldPassword" missingMessage="不能为空" required="true" name="oldPassword">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="password" class="input-text" placeholder="" id="password" missingMessage="不能为空" required="true" name="password">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>再次输入新密码：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="password" class="input-text" placeholder="" id="password1" missingMessage="不能为空" required="true" name="password1">
            </div>
        </div>
        <div class="row cl" style="margin-top: 50px;">
            <div class="col-xs-7 col-sm-8 col-xs-offset-3 col-sm-offset-2">
                <input onclick="submitHot()" class="btn  btn-block btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
        </form>
    </div>
</article>


<script type="text/javascript" >
    $("#form-admin").validate({
        rules:{
            oldPassword:{
                required:true,
                minlength:4,
                maxlength:16,
            },
            password:{
                required:true,
                minlength:4,
                maxlength:16,
            },
            password1:{
                required:true,
                minlength:4,
                maxlength:16,
                equalTo: "#password"
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            var index = parent.layer.getFrameIndex(window.name);
            parent.$('.btn-refresh').click();
            parent.layer.close(index);
        }
    });
    function submitHot(){
        if($("#oldPassword").val() == ""){
            layer.msg('原密码不能为空!');
            return false;
        }else if($("#password").val()==""){
            layer.msg('新密码不能为空!');
            return false;
        }else if(!($("#password").val()== $("#password1").val())){
            layer.msg('新密码两次输入不一致!');
            return false;
        } else {
            $.ajax({
                url:"changePassword.json",
                data:{
                    oldPassword:$("#oldPassword").val(),
                    password:$("#password").val()
                },
                success:function (data) {
                    if (data.code == 0) {
                        layer.msg("修改成功!", {icon: 1, time: 1000},function () {
                            layer_close();
                        });
                    } else {
                        layer.msg(data.msg);
                    }
                }
            });
        }

    }
</script>

</body>
</html>
