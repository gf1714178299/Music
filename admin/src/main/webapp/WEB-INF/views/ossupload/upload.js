var policyText = {
    "expiration": "2020-01-01T12:00:00.000Z", //设置该Policy的失效时间，超过这个失效时间之后，就没有办法通过这个policy上传文件了
    "conditions": [
        ["content-length-range", 0, 1048576000] // 设置上传文件的大小限制
    ]
};

accessid = 'LTAICDCw2dxTUZzM';
accesskey = 'HZzVV3qjxoS7zv0XZL0jo8WDsTaT5i';
host = 'http://nannong.oss-cn-beijing.aliyuncs.com';

var folder  = 'course/video/'
var policyBase64 = Base64.encode(JSON.stringify(policyText))
message = policyBase64
var bytes = Crypto.HMAC(Crypto.SHA1, message, accesskey, {asBytes: true});
var signature = Crypto.util.bytesToBase64(bytes);


var uploader = new plupload.Uploader({
    runtimes: 'html5,flash,silverlight,html4',
    browse_button: 'selectfiles',
    //runtimes : 'flash',
    container: document.getElementById('container'),
    flash_swf_url: 'lib/plupload-2.1.2/js/Moxie.swf',
    silverlight_xap_url: 'lib/plupload-2.1.2/js/Moxie.xap',

    url: host,

    multipart_params: {
        'Filename': '${filename}',
        'key': folder + '${filename}' ,
        'policy': policyBase64,
        'OSSAccessKeyId': accessid,
        'success_action_status': '200', //让服务端返回200,不然，默认会返回204
        'signature': signature
    },

    filters: {
        mime_types : [ //只允许上传mp4的视频文件
            { title : "Image files", extensions : "mp4,MP4" }
        ],
        //max_file_size : '400kb', //最大只能上传400kb的文件
        prevent_duplicates : true //不允许选取重复文件
    },

    init: {


        PostInit: function () {
            document.getElementById('ossfile').innerHTML = '';
            document.getElementById('postfiles').onclick = function () {
                uploader.start();
                return false;
            };
        },

        FilesAdded: function (up, files) {

            if(up.files.length>1) { // 最多上传1个文件
                alert("只能上传一个文件，请删除多余文件！");
                return;
            }else {
                plupload.each(files, function (file) {
                    document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
                        + '<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
                        + '</div>';
                });
            }
        },



        UploadProgress: function (up, file) {
            var d = document.getElementById(file.id);
            d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";

            var prog = d.getElementsByTagName('div')[0];
            var progBar = prog.getElementsByTagName('div')[0]
            progBar.style.width = 2 * file.percent + 'px';
            progBar.setAttribute('aria-valuenow', file.percent);
        },



        FileUploaded: function (up, file, info) {
            if (info.status >= 200 || info.status < 200) {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'success';
                alert("上传成功!");
            }
            else {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            }

        },


        Error: function (up, err) {
            document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
        }


    }
});


uploader.init();

