<%--
  Created by IntelliJ IDEA.
  User: Lokey
  Date: 2016/9/23
  Time: 10:03
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
    <%--list页面通用样式--%>
    <title>角色列表</title>
    <style>
        .center{
            /*表格内容居中*/
            text-align: center;
         }
    </style>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont"></i> 首页 <span class="c-gray en">&gt;
    </span> 管理员管理 <span class="c-gray en">&gt;
    </span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont"></i></a></nav>
<div class="page-container">

    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l"><a   href="javascript:;" style="display: none;" onclick="datadel()" class="btn btn-danger radius delete"><i class="Hui-iconfont"></i>批量删除</a>
                         <a   href="javascript:;" style="display: none;"  onclick="role_add('添加角色','rolesave.html','500','300')" class="btn btn-primary radius add"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a>
        </span>
    </div>

    <div class="mt-20">
        <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="9">角色列表</th>
        </tr>
        <tr class="text-c">
            <th><input type="checkbox" name="" value=""></th>
            <th>角色名</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        </table>
    </div>
</div>
</body>
<%--本页面js--%>
<script type="text/javascript">
    //本页id
    var menuId = 7;
    var addId = 8;
    var deleteId = 9;
    var changeId = 10;
    var addFlag = true;
    var deleteFlag = true;
    var changeFlag = true;
    var table;
    jQuery(function () {
        $(document).ready(function () {
            getButton();
            table = $("table").dataTable({
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
                "sAjaxSource": "role-list.json",//这个是请求的地址
                "fnServerData": retrieveData, // 获取数据的处理函数
//                "fnServerParams": function (aoData) {
//                    aoData.push(
////                            {"name": "delFlag", "value": $('#delFlag').val()}
//                    )
//                },
                "columnDefs": [
                    { "width": "2%", "targets": [0] },
                    { "width": "30%", "targets": [1] },
                    { "width": "30%", "targets": [2] },
                    { "width": "38%", "targets": [3] }
                ],
                "aoColumns": [
                    { "mData": "pkId",'sClass':'center',"mRender": function(data, type, full) {
                        var returnStr="";
                        returnStr += '<input type="checkbox" name="choice" value="'+full["pkId"]+'">';
                        return returnStr;
                    }},
                    { "mData": "name",'sClass':'center'},
                    {
                        "mData": "isEnabled", 'sClass': 'center', "mRender": function (data, type, full) {
                        var returnStr = "";
                        if (full["isEnabled"] == 1) {
                            returnStr = '<span class="label label-success radius">可用</span>';
                        }else {
                            returnStr = '<span class="label label-defaunt radius">不可用</span>';
                        }
                        return returnStr;
                    }
                    },
                    {
                        "mData": "Id", 'sClass': 'center', "mRender": function (data, type, full) {
                        var returnStr = "";
//                        if(!changeFlag){
////                        if (full["isEnabled"] == 0) {
////                            returnStr += '<i class="Hui-iconfont cursor-pointer change" title="停用" onClick="changeStatus(\'' + full["pkId"] + '\')">&#xe631;</i>';
////                        }else {
////                            returnStr += '<i class="Hui-iconfont cursor-pointer change" title="启用" onClick="changeStatus(\'' + full["pkId"] + '\')">&#xe631;</i>';
////                        }
//                            returnStr += '<a  style="text-decoration:none" class="ml-5 change" onClick="edit(\'编辑\',\'rolesave.html\',\'' + full["pkId"] + '\')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>';
//                            returnStr += '<a  style="text-decoration:none" class="ml-5 change" onClick="permisson(\'权限\',\'rolepermission.html\',\''+full["pkId"]+'\')" href="javascript:;" title="权限"><i class="Hui-iconfont">&#xe61d;</i></a>';
//                        }
//                        if(!deleteFlag) {
//                            returnStr += '<a  style="text-decoration:none" class="ml-5 delete" onClick="del(\'' + full["pkId"] + '\')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';
//                        }
                        returnStr += '<a  style="text-decoration:none" class="ml-5 change" onClick="edit(\'编辑\',\'rolesave.html\',\'' + full["pkId"] + '\')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>';
                        returnStr += '<a  style="text-decoration:none" class="ml-5 change" onClick="permisson(\'权限\',\'rolepermission.html\',\''+full["pkId"]+'\')" href="javascript:;" title="权限"><i class="Hui-iconfont">&#xe61d;</i></a>';
                        returnStr += '<a  style="text-decoration:none" class="ml-5 delete" onClick="del(\'' + full["pkId"] + '\')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';

                        return returnStr;
                    }
                    }
                ]
            });
//            $("#delFlag").change(function(){
//                var delFlag = $('#delFlag').val();
//                table.fnDraw();
//            })
        });
    });

    // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
    function retrieveData( sSource111,aoData111, fnCallback111) {
        $.ajax({
            url : sSource111,//这个就是请求地址对应sAjaxSource
            data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
            type : 'post',
            dataType : 'json',
            async : false,
            success : function(result) {
                fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
            },
            error : function(msg) {
            }
        });
    }

    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    /*增加*/
    function role_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    function permisson(title,url,id){
        var windowHeight = $(window).height();
        var windowWeight = $(window).width();
        var editirUrl = url + "?id=" + id;
        layer.open({
            type: 2,
            area: [windowWeight + 'px', windowHeight + 'px'],
            fix: false, //不固定
            maxmin: true,
            shade: 0.4,
            title: title,
            content: editirUrl,
            end: function () {
                table.fnDraw(true);
            }
        });
    }


    function edit(title,url,id){
        var w = 500;
        var h = 300;
        var editirUrl = url + "?id=" + id;
        layer.open({
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false, //不固定
            maxmin: true,
            shade: 0.4,
            title: title,
            content: editirUrl,
            end: function () {
                table.fnDraw(true);
            }
        });
    }

    /*删除*/
    function del(id){
        layer.confirm('确认要删除吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……
            $.ajax({
                url : "deleteRole.json",//这个就是请求地址对应sAjaxSource
                data : {
                    id:id
                },
                type : 'post',
                success : function(data) {
                    if (data.code == 0) {
                        layer.close(index);
                        layer.msg("删除成功");
                        table.fnDraw(true);
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error : function(msg) {
                    layer.msg('删除失败!');

                }
            });
        });
    }

    /*停用*/
    function changeStatus(id){
        $.ajax({
            url : "changeRoleStatus.json",
            data : {
                id:id
            },
            type : 'post',
            success : function(data) {
                if (data.code == 0) {
                    table.fnDraw(true);
                } else {
                    layer.msg(data.msg);
                }
            },
            error : function(msg) {
                layer.msg('状态修改失败!');

            }
        });
    }

    function datadel() {
        var ids = null;
        $("input[name='choice']:checkbox").each(function () {
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
            layer.confirm('确认要删除吗？', function (index) {
                $.ajax({
                    url: "deleteRoleList.json",
                    data: {
                        ids: ids
                    },
                    success: function (data) {
                        if (data.code == 0) {
                            layer.msg("删除成功");
                            table.fnDraw(true);
                        } else {
                            layer.msg(data.msg);
                        }
                    },
                    error: function (e) {
                        layer.msg('删除失败!');
                    }
                });
            })

        }
    }
    //按钮权限模块
   function getButton() {
        $(".add").each(function () {
            $(this).hide();
        })
        $(".delete").each(function () {
            $(this).hide();
        })
        $(".change").each(function () {
            $(this).hide();
        })
        $.ajax({
            url : "getPermissonByMenu.json",//这个就是请求地址对应sAjaxSource
            data : {
                menuId:menuId
            },
            type : 'post',
            success : function(data) {
                if (data.code == 0) {
                    for(var i=0;i<data.buttonList.length;i++){
                        if(data.buttonList[i]==addId){
                            $(".add").each(function () {
                                $(this).show();
                            })
                            addFlag = false;
                        }
                        if(data.buttonList[i]==deleteId){
                            $(".delete").each(function () {
                                $(this).show();
                            })
                            deleteFlag = false;
                        }
                        if(data.buttonList[i]==changeId){
                            $(".change").each(function () {
                                $(this).show();
                            })
                            changeFlag = false;
                        }
                    }
                    table.fnDraw(true);
                } else {
                    layer.msg(data.msg);
                }
            },
            error : function(msg) {
                layer.msg('获取按钮列表失败!');
            }
        });
    }


</script>
</html>
