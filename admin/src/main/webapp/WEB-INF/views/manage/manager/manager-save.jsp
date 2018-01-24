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
    <%--list页面通用样式--%>
    <script type="text/javascript" src="../H-ui/lib/laypage/1.2/laypage.js"></script>
    <script type="text/javascript" src="../H-ui/lib/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../H-ui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <%--管理页面通用样式--%>
    <title>添加/修改管理员</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-admin-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账号：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="text" class="input-text" value="${adminAccount}" placeholder="" id="account" name="account">
                <input type="hidden" id ="pkId" name="pkId" value="${pkId}">
            </div>
        </div>


        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色：</label>
        <div class="formControls col-xs-6 col-sm-6">
            <input type="hidden" id ="roleIdValue" name="roleIdValue" value="${roleId}">
            <input type="hidden" id ="expertIdValue" name="expertIdValue" value="${expertId}">
            <input type="hidden" id ="expertRoleId" name="expertRoleId" value="${expertRoleId}">
            <select id="roleId" class="select" name="roleId" size="1">
                <c:forEach  items="${roleList}" var="roleItem">
                    <option value='${roleItem.pkId}'>${roleItem.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

        <div class="row cl margin" id="user" style="display: none;">
            <label class="form-label col-xs-4 col-sm-3">搜索：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="text" id="keys" class="input-text radius size-M" style="width: 80%"
                       placeholder="专家手机号或昵称...">
                <input class="btn btn-primary radius" id="userSearch" type="button" value="搜索">

                <table class="table table-border table-bordered table-bg" id="userTable" style="margin-top: 20px;">
                    <thead>
                    <tr>
                        <th scope="col" colspan="9">用户列表</th>
                    </tr>
                    <tr class="text-c">
                        <th>操作</th>
                        <th>昵称</th>
                        <th>手机号</th>
                    </tr>
                    </thead>
                </table>
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
         $("#roleId").find('option[value=' + $("#roleIdValue").val() + ']').attr("selected", "selected");
        }
        if($("#expertRoleId").val() == $("#roleId").val()){
            $("#user").show();
        }
        $("#roleId").change(function () {
            if($("#expertRoleId").val() == $("#roleId").val()){
                $("#user").show();
            }else {
                $("#user").hide();
            }
        })


        $("#form-admin-add").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 2,
                    maxlength: 16
                },
                account: {
                    required: true,
                    minlength: 2,
                    maxlength: 16
                },
                roleId: {
                    required: true,
                },
                des: {
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
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.layer.close(index);
            }
        });

        // 查询用户
        $("#userSearch").click(function () {
            var keyWords = $("#keys").val();
            if (keyWords.trim().length > 0) {
                drawUser('%'+keyWords+'%');
            } else {
                layer.alert('请输入专家手机号或昵称！', {
                    skin: 'layui-layer-lan'
                    , closeBtn: 0
                    , anim: 4
                });
            }
        });

        //user查询
        function drawUser(key) {
         var table = $("#userTable").dataTable({
                "oLanguage": {
                    "sProcessing": "正在加载数据...",
                    "sLoadingRecords": "正在加载数据...",
                    "sLengthMenu": "显示_MENU_条 ",
                    "sZeroRecords": "没有您要搜索的内容",
                    "sInfo": "显示 _START_ 到 _END_ ，共 _TOTAL_ 条",
                    "sInfoFiltered": "(共 _MAX_ 条)",
                    "sInfoEmpty": "记录数为0",
                    "sInfoPostFix": "",
                    "sSearch": "内容搜索",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "第一页",
                        "sPrevious": " 上一页 ",
                        "sNext": " 下一页 ",
                        "sLast": " 最后一页 "
                    }
                },
                "sErrMode": "throw",
                "bDestroy": true,
                "bSort": false,
                "bStateSave": true,
                "bProcessing": false, // 是否显示取数据时的那个等待提示
                "bServerSide": true,//这个用来指明是通过服务端来取数据
                "sAjaxSource": "expert-list1.json",//这个是请求的地址
                "sServerMethod": "POST",
                "fnServerData": retrieveData, // 获取数据的处理函数
                "fnServerParams": function (aoData) {
                    aoData.push(
                            {name: "keyWords", value: key}
                    )
                },
                "columnDefs": [
                    {"width": "5%", "targets": [0]},
                    {"width": "45%", "targets": [1]},
                    {"width": "50%", "targets": [2]}
                ],
                "aoColumns": [
                    {
                        "mData": "pkId", 'sClass': 'center', "mRender": function (data, type, full) {
                        var returnStr = "";
                        returnStr += '<input type="radio" class="userRadio" name="userRadio" value="' + full["pkId"] + '">';
                        return returnStr;
                    }
                    },
                    {"mData": "nickname", 'sClass': 'center'},
                    {"mData": "account", 'sClass': 'center'}
                ]
            });
            $(".userRadio").change(function () {
                $("#expertIdValue").val($(this).val());
            })
        }

        function retrieveData(sSource111, aoData111, fnCallback111) {
            $.ajax({
                url: sSource111,//这个就是请求地址对应sAjaxSource
                data: {"aoData": JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (result) {
                    fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                },
                error: function (msg) {
                }
            });
        }
        //提交
        $("#submit").click(function () {
            var pkId =  $("#pkId").val();
            var name = $("#name").val();
            var account = $("#account").val();
            var roleId = $("#roleId").val();
            var isEnabled = $("#isEnabled").val();

            var dataJson = {"pkId": pkId,"account": account,"roleId": roleId,"isEnabled":isEnabled};
          if (account == null || account == '') {
                layer.msg("账号不能为空！");
            } else {
                layer.confirm('保存修改', function (f) {
                    if (f) {
                        $.ajax({

                            dataType: 'json',
                            url: "saveManager.json",
                            contentType: "application/json;charset=utf-8",
                            type: "post",
                            data: JSON.stringify(dataJson),
                            success: function (data) {
                                if (data.code == 0) {
                                    parent.location.reload();
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
<style>
    .dataTables_length{
        display: none;
    }
</style>
</html>
