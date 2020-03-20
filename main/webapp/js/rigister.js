layui.use('form', function () {
    var form = layui.form;

    form.verify({
        name: [
            /\p{Unified_Ideograph}/u, '请输入正确名称'
        ],
        confirmPass:function(value){
            if($('input[name=password]').val() !== value)
                return '两次密码输入不一致！';
        }

    })
    //监听提交
    form.on('submit(formDemo)', function (data) {
    });
    $(".layui-icon-password").click(function () {
        if ($(this).prev().attr("type")=="password"){
            $(this).prev().attr("type","text");
        }
        else if ($(this).prev().attr("type")=="text"){
            $(this).prev().attr("type","password");
        }
    })
});
function getObjectURL(file) {
    var url = null;
    var windowURL = window.URL || window.webkitURL;
    url = webkitURL.createObjectURL(file);
    return url;
}
$("#img").change(function () {
    $("label[for='img']").empty();
    $("label[for='img']").css("background-image", "url("+getObjectURL(this.files[0])+")");
});
$(".buttonback").click(function (e) { 
    $(this).animate({
        width: '0%',
    },250,function (e) {$(".buttonback>*").css("cursor", "not-allowed")});
    $(this).animate({
        width: '100%',
    },60000,function (e) {$(".buttonback>*").css("cursor", "pointer")});
});