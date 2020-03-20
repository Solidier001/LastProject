$(".fa-sort-desc").click(
    function () {
        if ($(this).parent().next().css("height") == "0px") {
            $(this).css("transform", "rotate(180deg)");
            $(this).parent().next().css("height", "50px")
        } else if ($(this).parent().next().css("height") == "50px") {
            $(this).css("transform", "rotate(0deg)");
            $(this).parent().next().css("height", "0px")
        }
    }
)
$(".fa-eye-slash").click(
    function () {
        if ($(this).hasClass("fa-eye-slash")) {
            $(this).parent().prev().children("input[type='password']").attr("type", "text");
            $(this).removeClass("fa-eye-slash").addClass("fa-eye");
        } else if ($(this).hasClass("fa-eye")) {
            $(this).parent().prev().children("input[type='text']").attr("type", "password");
            $(this).removeClass("fa-eye").addClass("fa-eye-slash");
        }
    }
)
$("input[name='method']").change(function () {
    switch ($(this).val()) {
        case "id":$("input[name='id']").attr("placeholder","账号");break;
        case "stuid":$("input[name='id']").attr("placeholder","学号");break;
        case "email":$("input[name='id']").attr("placeholder","email");break;
    }
})