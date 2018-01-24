<%--
  Created by IntelliJ IDEA.
  User: D丶Cheng
  Date: 2017/10/25
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="jsFile.jsp"%>
    <%--管理页面通用样式--%>
    <link rel="stylesheet" type="text/css" href="../H-ui/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../H-ui/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <%--管理页面通用样式--%>
    <title>主页-Musics管理后台</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"><a class="logo navbar-logo f-l mr-10 hidden-xs"
                                           href="#">Musics管理后台</a> <a
                class="logo navbar-logo-m f-l mr-10 visible-xs" href="#">Musics管理后台</a> <span
                class="logo navbar-slogan f-l mr-10 hidden-xs"></span> <a aria-hidden="false"
                                                                          class="nav-toggle Hui-iconfont visible-xs"
                                                                          href="javascript:;">&#xe667;</a>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${account}<i class="Hui-iconfont">
                        &#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a onClick="changePassword()">修改密码</a></li>
                            <li><a href="loginout.html">退出</a></li>
                        </ul>
                    </li>

                    <li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" class="dropDown_A"
                                                                               title="换肤"><i class="Hui-iconfont"
                                                                                             style="font-size:18px">
                        &#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>

<aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value=""/>
    <div class="menu_dropdown bk_2 menuDiv">

    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
</div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs" style="display:none">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S"
                                                  href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
                id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">
            &#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
        </div>
    </div>
</section>

<script type="text/javascript">
    var _menus = {};
    function changePassword(title){
        var w = 800;
        var h = 400;
        layer_show('修改密码','changePassword.html',w,h);
    }

    $(function () {
        $.ajax({
            type: 'GET',
            url: 'getMenu.json',
            async: false,//同步
            dataType: 'json',
            success: function (data) {
                _menus = data;
            },
            error: function (xhr, status, error) {
                alert("获取功能列表失败，请重新登录");
            }
        });
        InitLeftMenu();
    })
    //初始化左侧
    function InitLeftMenu() {
        var menuHtml = '';
        $.each(_menus.menus, function (i, n) {
              menuHtml = menuHtml + '<dl><dt><i class="Hui-iconfont"></i>&nbsp;&nbsp;' + n.ModelName + '<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt><dd><ul>';
              $.each(n.secondList, function (j, o) {
                  menuHtml = menuHtml +'<li><a data-href="' + o.url + '" data-title="' + o.name + '" href="javascript:;">' + o.name + '</a></li>';
              })
            menuHtml = menuHtml  +'</ul></dd></dl>';
        })
//        menuHtml = menuHtml +'<dl><dt><i class="Hui-iconfont"></i>&nbsp;&nbsp;统计管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt><dd><ul>';
//        menuHtml = menuHtml  +'<li><a data-href="http://localhost:8080/manage/expert-count.html" data-title="专家绩效分数统计" href="javascript:;">专家绩效分数统计</a></li>';
//        menuHtml = menuHtml  +'<li><a data-href="http://localhost:8080/manage/user-city.html" data-title="人员统计" href="javascript:;">人员统计</a></li>';
//        menuHtml = menuHtml  +'<li><a data-href="http://localhost:8080/manage/user-active.html" data-title="用户活跃度统计" href="javascript:;">用户活跃度统计</a></li>';
//        menuHtml = menuHtml  +'<li><a data-href="http://localhost:8080/manage/user-statics.html" data-title="新型农业经营主题统计" href="javascript:;">新型农业经营主题统计</a></li>';
//        menuHtml = menuHtml  + '</ul></dd></dl>'
        $(".menuDiv").html(menuHtml)

        $(".Hui-aside").Huifold({//组建注册
            titCell:'.menu_dropdown dl dt',
            mainCell:'.menu_dropdown dl dd',
        });
    }
</script>
</body>
</html>
