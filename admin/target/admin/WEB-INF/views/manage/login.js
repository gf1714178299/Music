jQuery(function(){
    $('#username').keypress(function(event){
        var keynum = (event.keyCode ? event.keyCode : event.which);
        if(keynum == '13'){
            $('#password').focus();
        }
    });

    $('#password').keypress(function(event){
        var keynum = (event.keyCode ? event.keyCode : event.which);
        if(keynum == '13'){
            chklogin();
        }
    });
    $("#loginbtn").click(function(){
        chklogin();
    });
});
function chklogin(){
    var account = $('#username').val();
    var password = $('#password').val();
    $.ajax({
        type: "Post",
        url: "loginCheck.json",
        data: {account:account,password:password},
        dataType: "json",
        success: function(data){
            if(data.code==0){
                layer.msg('登录成功,系统进入中!',{icon:1,time:1000},function(){
                    parent.location.href="index.html";
                });
            }else{
                layer.msg(data.msg, {icon:1,time:1000});
            }
        },
        error: function(e) {
            layer.msg('登录失败!',{icon:1,time:1000});
        }
    });
}