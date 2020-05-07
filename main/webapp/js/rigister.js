layui.use('form', function () {
    var form = layui.form;

    form.verify({
        name: [
            /\p{Unified_Ideograph}/u, '请输入正确名称'
        ],
        confirmPass: function (value) {
            if ($('input[name="password"]').val() != value)
                return '两次密码输入不一致！';
        },
        class: [
            /\d*/, '班号格式不正确'
        ],
        verifyOAth: function (value) {
            $.post("/project/account/verifyoath",
                {OAth: value},
                function (data) {
                    if (data == "false") return '验证码错误'
                })
        }

    })
    //监听提交
    form.on('submit(formDemo)', function (data) {
    });

    form.on('select(faculty)', function (data) {
        if (data.value != "") {
            $.post("/project/page/getspecialty",
                {faculty: data.value},
                function (data) {
                    var option = document.createElement("option");
                    $(option).attr("value", "");
                    $(option).html("请选择专业");
                    var list = JSON.parse(data);
                    $("select[name='specialty']").empty();
                    $("select[name='specialty']").append(option);
                    for (var i = 0; i < list.length; i++) {
                        var option = document.createElement("option");
                        $(option).attr("value", list[i]);
                        $(option).html(list[i]);
                        $("select[name='specialty']").append(option);
                    }
                    form.render('select');
                })
        } else {
            var option = document.createElement("option");
            $(option).attr("value", "");
            $(option).html("无");
            $("select[name='specialty']").empty();
            $("select[name='specialty']").append(option);
            form.render('select');
        }
    });
    $(".layui-icon-password").click(function () {
        if ($(this).prev().attr("type") == "password") {
            $(this).prev().attr("type", "text");
        }
        else if ($(this).prev().attr("type") == "text") {
            $(this).prev().attr("type", "password");
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
    $("label[for='img']").css("background-image", "url(" + getObjectURL(this.files[0]) + ")");
});
$(".buttonback").click(function (e) {
    var email = $("input[name='email']").val();
    if (email != null && email != "") {
        $(this).animate({
            width: '0%',
        }, 250, function (e) {
            layer.msg(email)
            $.post("/project/account/sendcode", {email: email});
            $(".buttonback>*").css("cursor", "not-allowed")
        });
        $(this).animate({
            width: '100%',
        }, 60000, function (e) {
            $(".buttonback>*").css("cursor", "pointer")
        });
    } else {
        layer.msg("未填写邮箱")
    }
});