/**
 * Created by D丶Cheng on 2017/11/16.
 * 基于H-ui的ajax封装，挺高代码复用性，减少代码冗余度
 */
var AjaxUtil = (function (w) {
    var AjaxUtil = function (obj) {
        return new AjaxUtil.fn.init(obj);
    }

    AjaxUtil.fn = AjaxUtil.prototype = {
        constructor: AjaxUtil,
        /**
         * 初始化
         */
        init: function () {
            this.type = "POST"; //请求类型
            this.data = {}; //请求参数
            this.dataType = "json"; //返回类型
            this.contentType = "application/x-www-form-urlencoded"; //参数类型
        },
        // /**
        //  * 查看参数
        //  */
        // showParameter: function () {
        //     console.log(this);
        // },
        /**
         * 设置ajax相关值
         * @param obj 对象数组
         * @private
         */
        _set: function (obj) {
            this.url = obj.url; //请求地址
            this.type = obj.type || "POST"; //请求类型
            this.data = obj.data || {}; //请求参数
            this.dataType = obj.dataType || "json"; //返回类型
            this.contentType = obj.contentType || "application/x-www-form-urlencoded"; //参数类型
        },
        /**
         * 添加、编辑操作
         * @param layer 弹框插件
         */
        ajaxAdd: function () {
            $.ajax({
                type: this.type,
                url: this.url,
                contentType: this.contentType,
                data: this.data,
                dataType: this.dataType,
                success: function (data) {
                    if (data.code == 0) {
                        parent.location.reload();
                        layer_close();
                    } else {
                        layer.msg(data.msg);
                    }
                }
            });
        },
        /**
         * 删除操作
         * @param layer 弹框
         * @param table 数据表格
         * @param index 窗口索引
         */
        ajaxDelete: function (table, index) {
            $.ajax({
                url: this.url,
                data: this.data,
                type: this.type,
                success: function (data) {
                    if (data.code == 0) {
                        layer.close(index);
                        layer.msg("删除成功");
                        table.fnDraw(true);
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (data) {
                    layer.msg(data.msg);
                }
            });
        },
        /**
         * 修改状态
         * @param layer
         * @param table
         */
        changeStatus: function (table) {
            $.ajax({
                url: this.url,
                data: this.data,
                type: this.type,
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg(data.msg);
                        table.fnDraw(true);
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (msg) {
                    layer.msg('状态修改失败!');
                }
            });
        },
        /**
         * 获取json类型的contentType
         * @returns {string}
         */
        getJsonContentType: function () {
            return "application/json; charset=utf-8";
        },
        /**
         * 获取页面checkbox选中的参数
         * @param boxName checkbox的参数名
         */
        getCheckboxVal: function () {
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
            return ids;
        },


        /**** 弹框操作 ****/

        /**
         * 修改或查看界面
         * @param title 窗口标题
         * @param url   请求地址
         * @param table 数据表格
         * @param windowWeight 窗口宽度
         * @param windowHeight  窗口高度
         *
         *  ps: 当且仅当windowWeight和windowHeight
         *      都不为空时窗口才按尺寸打开，否则默认全屏
         * @private
         */
        edit_show: function (title, url, table, windowWidth, windowHeight) {
            if (title == null || title == '') {
                title = false;
            }
            ;
            var isFull = false; //判断弹框是否要全屏
            if ((windowHeight == "" || windowHeight == null) || (windowWidth == "" || windowWidth == null)) {
                windowWidth = $(window).width();
                windowHeight = $(window).height();
                isFull = true;
            }
            var index = layer.open({
                type: 2,
                title: title,
                content: url,
                area: [windowWidth + 'px', windowHeight + 'px'],
                maxmin: true,
                end: function () {
                    if (table != null) { //如果table存在就不重绘table
                        table.fnDraw(true);
                    }
                }
            });
            if (isFull === true) {
                layer.full(index);
            }
        },
        /**
         * 显示详情
         * @param des
         */
        info_show: function (des) {
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '240px'], //宽高
                content: "<div style='width: 98%;margin: 2px auto;word-wrap: break-word;'>&nbsp;&nbsp;&nbsp;&nbsp;" + des + '</div>'
            });
        }
    }

    AjaxUtil.fn.init.prototype = AjaxUtil.fn;

    return AjaxUtil;
})();