<%--
  Created by IntelliJ IDEA.
  User: lokey
  Date: 2017/7/10
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <%@include file="../jsFile.jsp"%>
    <%--管理页面通用样式--%>
    <link rel="stylesheet" type="text/css" href="../H-ui/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../H-ui/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <%--管理页面通用样式--%>
    <title>添加/修改角色</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-admin-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="text" class="input-text" value="${name}" placeholder="" id="name" name="name">
                <input type="hidden" id ="pkId" name="pkId" value="${pkId}">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否可用：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="hidden" id ="isEnabledValue" name="isEnabledValue" value="${isEnabled}">
                <select id="isEnabled" class="select" name="isEnabled" size="1">
                        <option value='1'>可用</option>
                        <option value='2'>不可用</option>
                </select>
            </div>
        </div>


        <div class="row cl" style="margin-top: 50px;">
        <div class="col-xs-6 col-sm-6 col-xs-offset-3 col-sm-offset-2">
            <input id="submit" class="btn  btn-block btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
        </div>
    </div>
    </form>
</article>


<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    $(function () {
        if($("#pkId").val()!=""&&$("#pkId").val()!=null){
         $("#isEnabled").find('option[value=' + $("#isEnabledValue").val() + ']').attr("selected", "selected");
        }
        $("#form-admin-add").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 2,
                    maxlength: 16
                },
                isEnabled: {
                    required: true,
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
            }
        });


        $("#submit").click(function () {
            var pkId =  $("#pkId").val();
            var name = $("#name").val();
            var isEnabled = $("#isEnabled").val();
            var dataJson = {"pkId": pkId,"name": name,"isEnabled":isEnabled};
            if (name == null || name == '') {
                layer.msg("角色名称不能为空！");
            } else {
                layer.confirm('保存修改', function (f) {
                    if (f) {
                        $.ajax({

                            dataType: 'json',
                            url: "saverole.json",
                            contentType: "application/json;charset=utf-8",
                            type: "post",
                            data: JSON.stringify(dataJson),
                            success: function (data) {
                                if (data.code == 0) {
                                    parent.location.reload();
                                    layer.msg('新增成功!');
                                    setTimeout(PageLoad, 2000);
                                    layer_close();
                                } else {
                                    layer.msg(data.msg);
                                }
                            }
                        });
                    }
                });
            }

            function PageLoad() {
                window.location.reload();
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
