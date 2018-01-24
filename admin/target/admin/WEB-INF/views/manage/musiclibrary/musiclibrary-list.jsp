<%--
  Created by IntelliJ IDEA.
  User: GF
  Date: 2017/10/25
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <%@include file="../jsFile.jsp"%>
    <%--管理页面通用样式--%>
    <link rel="stylesheet" type="text/css" href="../../H-ui/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../../H-ui/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <%--管理页面通用样式--%>
    <%--list页面通用样式--%>
    <script type="text/javascript" src="../../H-ui/lib/laypage/1.2/laypage.js"></script>
    <script type="text/javascript" src="../../H-ui/lib/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../../H-ui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <%--list页面通用样式--%>
    <title>音乐列表</title>
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
    </span> 音乐管理 <span class="c-gray en">&gt;
    </span> 音乐管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont"></i></a>
</nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l"><a   href="javascript:;" style="" onclick="datadel()" class="btn btn-danger radius delete"><i class="Hui-iconfont"></i>批量删除</a>
                         <a   href="javascript:;" style=""  onclick="music_add('添加歌曲','musicAdd.html','500','300')" class="btn btn-primary radius add"><i class="Hui-iconfont">&#xe600;</i> 添加歌曲</a>
        </span>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <div class="col-sm-12">
            <span>搜索</span>
            <input type="text" class="input-text" placeholder="歌名/歌手名" id="keyWords" style="width:150px;">
            <input class="btn radius btn-secondary" id="search" type="button" value="搜索">
        </div>
    </div>

    <div class="mt-20">
        <table class="table table-border table-bordered table-bg">
            <thead>
            <tr>
                <th scope="col" colspan="10">音乐列表</th>
            </tr>
            <tr class="text-c">
                <th><input type="checkbox" name="" value=""></th>
                <th>音乐名称</th>
                <th>歌手名称</th>
                <th>音乐封面</th>
                <th>点击次数</th>
                <th>所属专辑</th>
                <th>风格</th>
                <th>出版日期</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    var menuId = 13;
    var addId = 14;
    var deleteId = 15;
    var changeId = 16;
    var addFlag = true;
    var deleteFlag = true;
    var changeFlag = true;
    var table;
    jQuery(function () {
        var prefix = ResourcesPrefix();
        $(document).ready(function () {
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
                "sAjaxSource": "musiclibrary-list.json",//这个是请求的地址
                "fnServerData": retrieveData, // 获取数据的处理函数
                "fnServerParams": function (aoData) {
                    aoData.push(
                        {"name": "keyWords", "value": $('#keyWords').val()}
                    )
                },
                "columnDefs": [
                    {"width": "4%", "targets": [0]},
                    {"width": "10%", "targets": [1]},
                    {"width": "10%", "targets": [2]},
                    {"width": "10%", "targets": [3]},
                    {"width": "10%", "targets": [4]},
                    {"width": "10%", "targets": [5]},
                    {"width": "10%", "targets": [6]},
                    {"width": "10%", "targets": [7]},
                    {"width": "10%", "targets": [8]},

                ],
                "aoColumns": [
                    {
                        "mData": "pkId", 'sClass': 'center', "mRender": function (data, type, full) {
                        var returnStr = "";
                        returnStr += '<input type="checkbox" name="choice" value="' + full["pkId"] + '">';
                        return returnStr;
                    }
                    },
                    {"mData": "musicName", 'sClass':'center'},
                    {"mData": "singerName", 'sClass': 'center'},
                    { "mData": "musicImgUrl",'sClass':'center',"mRender": function(data, type, full) {
                        var returnStr="";
                        var imgSrc = prefix + full['musicImgUrl'];
                        returnStr += '<a style="text-decoration:none" class="ml-5" href="javascript:;"><img style="height: 80px;width: 80px" class="img" src=\'' + imgSrc + '\'/> </a>';
                        return returnStr;
                    }},
                    {"mData": "clicks", 'sClass': 'center'},
                    {"mData": "albumName", 'sClass': 'center'},
                    {"mData": "singerStyleName", 'sClass': 'center'},
                    {"mData": "publishDate", 'sClass': 'center'},
                    {
                        "mData": "pkId", 'sClass': 'center', "mRender": function (data, type, full) {
                        var returnStr = "";
                        returnStr += '<a style="text-decoration:none" class="ml-5 change" onClick="edit(\'编辑优惠券\',\'couponsSave.html\',\'' + full["pkId"] + '\')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>';

                        //   returnStr += '<a style="text-decoration:none" class="ml-5" onClick="safetyLockDetails(\'查看详情\',\'safetyLockDetails.html\',\'' + full["pkId"] + '\')" href="javascript:;" title="查看详情"><i class="Hui-iconfont">&#xe627;</i></a>';
//                        returnStr += '<a style="text-decoration:none" class="ml-5" onClick="datadel(\'' + full["pkId"] + '\')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';
//                            returnStr += '<a style="text-decoration:none" class="ml-5 change"  onClick="news_up(\''+full["pkId"]+'\',true)" href="javascript:;" title="向上"><i class="Hui-iconfont">&#xe679;</i></a>';
//                            returnStr += '<a style="text-decoration:none" class="ml-5 change"  onClick="news_up(\''+full["pkId"]+'\',false)" href="javascript:;" title="向下"><i class="Hui-iconfont">&#xe674;</i></a>';
                        return returnStr;
                    }
                    }
                ]
            });
        });
        $("#keyWords").bind("change", function () {
            table.fnDraw();
        })
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

    //批量删除
    function datadel(pkId) {
        var ids = null;
        var ids = pkId;
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
                    url: "deleteAppConfigList.json",
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
    /*增加*/
    function music_add(title,url,w,h){
        layer_show(title,url,$(window).width(),$(window).height());
    }
    //编辑
    function edit(title,url,pkId){
        var w = $(window).width();
        var h = $(window).height();
        var editirUrl = url + "?pkId=" + pkId;
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
    $(document).on("click", '.img', function () {
        var $img = '<img style="height: 200px;width: 200px" src=\'' + this.src + '\'/>'
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: '200px',
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: $img
        });
    })
</script>
</html>
