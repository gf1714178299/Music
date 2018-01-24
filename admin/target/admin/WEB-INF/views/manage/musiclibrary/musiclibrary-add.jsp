<%@ taglib prefix="required" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: D丶Cheng
  Date: 2017/7/25
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <%@include file="../jsFile.jsp" %>
    <%--管理页面通用样式--%>
    <link rel="stylesheet" type="text/css" href="../H-ui/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="../H-ui/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <%--list页面通用样式--%>
    <script type="text/javascript" src="../H-ui/lib/laypage/1.2/laypage.js"></script>
    <script type="text/javascript" src="../H-ui/lib/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../H-ui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../ossupload/style.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <%--voideo标签css--%>
    <link href="http://vjs.zencdn.net/5.11.7/video-js.css" rel="stylesheet">
    <%--管理页面通用样式--%>
    <title>添加音乐</title>
    <style>
        .report-file span {
            cursor: pointer;
            display: block;
            line-height: 28px;
        }

        #upload {
            width: 80%;
            margin: 20px auto;
            text-align: center;
        }

        #textName {
            width: 300px;
        }

        .margin {
            margin-top: 15px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont"></i> 首页 <span class="c-gray en">&gt;
    </span> 音乐模块 <span class="c-gray en">&gt;
    </span> 添加音乐 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                    href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont"></i></a>
</nav>
<article class="page-container">
    <div class="form form-horizontal">
        <input type="hidden" class="input-text" placeholder="" id="courseId" name="courseId" value="${courseId}">
        <div class="row cl margin" id="title">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>音乐名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input id="musicName" type="text" class="input-text input-text radius size-M title" autocomplete="off"
                       placeholder="音乐名称">
            </div>
        </div>
        <div class="row cl margin" id="title">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌手名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input id="singerName" type="text" class="input-text input-text radius size-M title" autocomplete="off"
                       placeholder="歌手名">
            </div>
        </div>
        <div class="row cl margin" id="title">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>专辑名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input id="album" type="text" class="input-text input-text radius size-M title" autocomplete="off"
                       placeholder="专辑名">
            </div>
        </div>
        <%--<c:if test="${courseId == null}">--%>
            <%--<div class="row cl margin">--%>
                <%--<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>选择风格：</label>--%>
                <%--<div class="formControls col-xs-8 col-sm-9">--%>
                <%--<span id="course" class="select-box radius">--%>
                    <%--<select class="select" size="1" name="demo1">--%>
                        <%--<c:forEach items="${musicStyleList}" var="course" varStatus="vs">--%>
                            <%--<option value="${musicStyleList.pkId}" selected="">${musicStyleList.styleName}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>选择风格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select" id="musicStyle" name="musicStyle" >
                    <option value="-1">请选择</option>
                </select>
            </div>
        </div>
        <div class="row cl margin" id="video">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>MV：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
                <div id="container">
                    <a id="selectfiles" href="javascript:void(0);" class='btn'>添加MV</a>
                    <a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a><span class=" c-666">(最好选择后缀为mp4的视频文件)</span>
                </div>
            </div>
        </div>

        <video id="videoAddress" src="" width="0px" height="0px">
        </video>
        <%--<input type="button" onclick="videoAddress()" value="videoAddress">--%>

        <div class="row cl margin">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>MV封面：</label>
            <div id="imgDiv" style="display: none;margin: 0 auto;">
                <img style="height: 120px;width: 120px" id="img" class="img" src='${cover}'/> </a>
            </div>
            <div id="upload">
                <form id="imgForm" enctype="multipart/form-data">
                    <input type="text" class="radius" id="textName" style="height: 28px;border:1px solid #f1f1f1"
                           disabled/>
                    <span class="btn-upload">
                        <a class="btn btn-primary radius cursor-pointer">浏览文件</a>
                        <input type="file" multiple class="input-file" id="coverFile" name="file"
                               onchange="uploadImg(1,1)">
                    </span>
                </form>
                <span class=" c-666">(图片宽高比限制1:1 大小不超过200k)</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>发布时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <script language="javascript" type="text/javascript" src="../../H-ui/static/My97DatePicker/WdatePicker.js"></script>
                <input class="Wdate" value="${productionDate}" type="text" readonly  unselectable="on" id="productionDate" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                <%--<input readonly="readonly" type="text" class="Wdate" id="productionDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />--%>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select" id="state" name="state" value="${state}">
                    <option value="-1">请选择</option>
                    <option value="0">开放</option>
                    <option value="1">关闭</option>
                </select>
            </div>
        </div>
        <div class="row cl margin">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" id="submit" type="submit" value="提交">
            </div>
        </div>
    </div>

</article>

<!--/_footer /作为公共模版分离出去-->
<script type="text/javascript">
    getAllMusicStyle();
    var prefix = ResourcesPrefix();
    var imgUrl = ''; //图片路径
    var table;
    var oImg = document.getElementById('img');
    var oDiv = document.getElementById('imgDiv');
    //上传图片
    function uploadImg(imgWidth, imgHeight) {
        if (!checkFileExt($("#coverFile").val())) {
            layer.msg("文件名不合法", {icon: 5, time: 1000});
            return;
        }
        var file = document.getElementById('coverFile').files[0];
        var formData = new FormData();  // 要求使用的html对象
        formData.append("file", file);
        formData.append("width", imgWidth);
        formData.append("height", imgHeight);
        $.ajax({
            url: 'uploadImg.action',
            type: 'POST',
            data: formData,
            async: true,
            // 下面三个参数要指定，如果不指定，会报一个JQuery的错误
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('添加成功', {icon: 1, time: 1000});
                    imgUrl = data.imgUrl;
                    oImg.src = prefix + imgUrl;
                    oDiv.style.display = 'block';
                    document.getElementById('textName').value = imgUrl;
                    document.getElementById('videoAddress').src = prefix+getName();
//                    alert(document.getElementById('videoAddress').src);
                } else {
                    document.getElementById('textName').value = '';
                    layer.msg(data.msg, {icon: 5, time: 1000});
                }
            },
            error: function (e) {
                document.getElementById('textName').value = '';
                layer.msg('上传失败!', {icon: 5, time: 1000});
            }
        });
    }
    function getAllMusicStyle(){
        $.ajax({
            url:"selectMusicStyleListByPage.json",/*icon-model.json*/
            data:{
                fatherId:$("#musicStyle").val(),
            },
            success:function (data) {
                /* console.log(data.data)*/

                $.each(data.data,function (index,value) {
                    if(value.flag != 2){
                        $option = $('<option value="'+value.pkId+'">'+value.styleName+'</option>');
                        $("#musicStyle").append($option);
                    }

                });
            }
        });
    }
    $("#submit").click(function () {
        var courseId = "";
        if (typeof ($('#course option:selected').val()) == "undefined") {
            courseId = $("#courseId").val();
        } else {
            courseId = $('#course option:selected').val();
        }
        var title = $("musicName").val();
        var videoUrl = getName();
        var videoLength = getVideoLength();
        var musciImgUrl = imgUrl;
        var isComplete = false;
        var isUpload = false;
        var successFlag = $('#ossfile  b').text();
        if (successFlag == 'success') {
            isUpload = true;
        }

        if (courseId != null && cover != ""
            && title != "" && typeof (videoUrl) != "undefined") {
            isComplete = true;
        }

        if (isComplete == false) {
            layer.alert('请输入完整信息', {
                skin: 'layui-layer-lan'
                , closeBtn: 0
                , anim: 4
            });
        } else {
            if (isUpload == false) {
                layer.alert('请把视频上传！', {
                    skin: 'layui-layer-lan'
                    , closeBtn: 0
                    , anim: 4
                });
            } else {
                var formData = new FormData();
                formData.append("courseId", courseId);
                formData.append("videoLength", videoLength);
                formData.append("url", videoUrl);
                formData.append("cover", cover);
                formData.append("title", title);
                $.ajax({
                    url: "video-add.json",
                    type: "POST",
                    data: formData,
                    cache: false,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                        if ($('#type').val() == "iframe" || $("#courseId").val() != '') {
                            parent.location.reload();
                        } else {
                            location.replace(location.href);
                        }
                    },
                    error: function (data) {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }
                });
            }
        }
    });

    function checkFileExt(filename) {
        var flag = false; //状态
        var arr = ["jpg", "png", "jpeg"];
        var index = filename.lastIndexOf(".");
        var ext = filename.substr(index + 1);
        for (var i = 0; i < arr.length; i++) {
            if (ext.toLowerCase() == arr[i].toLowerCase()) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    function getName() {
        var fileName = $(".progress").parent().text();
        fileName = fileName.substring(0, fileName.lastIndexOf(' ('));
        fileName = "course/video/" + fileName;
        return fileName;
    }


    function  getVideoLength() {
        var fileLength =parseInt(document.getElementById("videoAddress").duration) ;
        return fileLength;
    }

</script>
</body>
<script type="text/javascript" src="../../ossupload/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="../../ossupload/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="../../ossupload/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="../../ossupload/lib/base64.js"></script>
<script type="text/javascript" src="../../ossupload/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="../../ossupload/upload.js"></script>
</html>
