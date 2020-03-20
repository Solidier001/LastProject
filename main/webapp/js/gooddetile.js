function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

layui.use('carousel', function () {
    var carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#test1',
        width: '500px',
        height: '500px',
        arrow: 'always', //始终显示箭头
        anim: 'updown' //切换动画方式
    });
});
$("#buy").click(
    function () {
        location = "/project/good/makeorder.action?id=" + $(this).attr("g_id");
    }
);
$("#talk").click(function () {
    location = "/sevice.html?id=" + $(this).attr("own_id") + '&' + "u_id=" + $(this).attr("u_id") + '&u_name=' + $(this).attr("u_name");
});
function lodereview(){
$.post("/project/good/reviewlist", {
    id: $("#buy").attr("g_id")
}, function (data) {
    if (data != 'null'){
        $(".noreview").remove();
        $(".reviewitem").remove();
        var list = JSON.parse(data);
        for (var i = 0; i < list.length; i++) {
            $.ajax({
                url: list[i],
                type: 'post',
                dataType: 'xml',
                success: function (data) {
                    var reviewitem = document.createElement("div");
                    var lable = document.createElement("div");
                    var img = document.createElement("div");
                    var name = document.createElement("div");
                    var date = document.createElement("div");
                    var text = document.createElement("div");
                    $.post("/project/account/test",
                        function (data, status, xhr) {
                            if (data != "offline") {
                                var user = JSON.parse(data);
                                $(img).css("background-image","url('/img/portait/"+user.img+"')");
                                $(name).text(user.username);
                            }});
                    $(reviewitem).addClass("reviewitem");
                    $(lable).addClass("lable");
                    $(img).addClass("img");
                    $(name).addClass("name");
                    $(date).addClass("date");
                    $(date).text($(data).find("date").text());
                    $(text).addClass("text");
                    $(text).html($(data).find("text").html().replace("\n","</p><p>"));
                    $(lable).append(img)
                    $(lable).append(name)
                    $(lable).append(date)
                    $(reviewitem).append(lable)
                    $(reviewitem).append(text)
                    $(".reviewbox").append(reviewitem);
                    $(data).find("uid")
                    $(data).find("date");
                }
            });
        }
    }
})}
lodereview();
$.post("/project/account/test",function (data) {
    if (data!="offline"){
        $(".button").text("评论");
        $(".button").click(function () {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.open({
                    type: 2,
                    title: '所有评论',
                    shadeClose: true,
                    shade: false,
                    maxmin: false, //开启最大化最小化按钮
                    area: ['400px', '500px'],
                    content: '/writereview.html?id='+getUrlParam("id"),
                    offset: 't',
                    cancel: function(index, layero){
                        lodereview()
                    }
                    })})
        })
    }
})