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
    <title>角色权限</title>
</head>
<body>
<input type="hidden" id="pkId" value="${id}">
<article class="page-container">
    <form class="form form-horizontal" id="form-admin-add">
        <div class="formControls col-xs-12 col-sm-12">
           <c:forEach  items="${permissionList}" var="model">
            <dl class="permission-list">
                <dt>
                    <label>
                        <input type="checkbox" value="${model.pkId}" name="user-Character-0" id="user-Character-0">
                        ${model.name}</label>
                </dt>
                <dd>
                <c:forEach  items="${model.sons}" var="menu">
                    <dl class="cl permission-list2">
                        <dt>
                            <label class="">
                                <input type="checkbox" value="${menu.pkId}" name="user-Character-0-0" id="user-Character-0-0">
                                ${menu.name}</label>
                        </dt>
                        <dd>
                        <c:forEach  items="${menu.sons}" var="button">
                            <label class="">
                                <input type="checkbox" value="${button.pkId}" name="user-Character-0-0-0" id="user-Character-0-0-0">
                               ${button.name}</label>
                        </c:forEach>
                        </dd>
                    </dl>
                </c:forEach>
                </dd>
            </dl>
           </c:forEach>
        </div>

            <div class="col-xs-6 col-sm-6 col-xs-offset-3 col-sm-offset-2" style="margin-top: 50px;">
                <input id="submit" class="btn  btn-block btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>

    </form>
</article>


<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
    //权限复选框
    $(".permission-list dt input:checkbox").click(function(){
        $(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
    });
    $(".permission-list2 dd input:checkbox").click(function(){
        var l =$(this).parent().parent().find("input:checked").length;
        var l2=$(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
        if($(this).prop("checked")){
            $(this).closest("dl").find("dt input:checkbox").prop("checked",true);
            $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
        }
        else{
            if(l==0){
                $(this).closest("dl").find("dt input:checkbox").prop("checked",false);
            }
            if(l2==0){
                $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
            }
        }
    });
    $(function () {
        $.ajax({
            url : "promissionList.json",
            data : {
                id:$("#pkId").val()
            },
            type : 'post',
            success : function(data) {
                if (data.code == 0) {
                    var permissionList = data.data;
                   for(var i = 0;i<permissionList.length;i++){
                       $(".formControls").find('input[value=' + permissionList[i].resourceId + ']').prop("checked", true);
                   }
                } else {
                    layer.msg('获取角色权限失败，请刷新后重试!');
                }
            },
            error : function(msg) {
                layer.msg('获取角色权限失败，请刷新后重试!');
            }
        });
    })


    $("#submit").click(function () {
        var ids = null;
        $("input:checkbox").each(function () {
            if ($(this).prop("checked")) {
                if (ids == null) {
                    ids = $(this).val();
                } else {
                    ids += ',' + $(this).val();
                }
            }
        });
        if (ids == null) {
            layer.msg("至少选择一条数据！");
        } else {
            layer.confirm('确认要提交？', function (index) {
                $.ajax({
                    url: "changePermission.json",
                    data: {
                        id:$("#pkId").val(),
                        ids: ids
            },
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg("权限修改成功");
                        layer_close();
                    } else {
                        layer.msg("权限修改失败");
                    }
                },
                error: function (e) {
                    layer.msg("权限修改失败");
                }
            });
            })

        }
    })
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
