var num = 0;
$.post("/project/account/test",
    function (data, status, xhr) {
        if (data != "offline") {
            var user = JSON.parse(data);
            $(".login>div").text(user.username);
            $(".nav>.login").mouseover(function () {
                $(".nav+div").css("height", "100px");
            });
            $(".nav>.login").mouseout(function () {
                $(".nav+div").css("height", "0px");
            });
        }
    }
)
$.post("/project/page/listNumber",
    {ListName: "all"},
    function (data, status, xhr) {
        num = parseInt(data);
    }
)
$(".nav>.login").click(function (e) {
    location = "/index.html";
});
$(".nav>.links").click(function (e) {
    $(".nav>.links").removeClass("linkafterclick");
    $(this).addClass("linkafterclick");
    location = $(this).attr("href");
});
layui.use('carousel', function () {
    var carousel = layui.carousel;
    carousel.render({
        elem: '#carousel',
        width: '1000px',
        height: '400px',
        arrow: 'always',
        arrow: 'none'
    });
});
layui.use('laypage', function () {
    var laypage = layui.laypage;
    laypage.render({
        prev: '<i class="layui-icon layui-icon-prev"></i> ',
        next: '<i class="layui-icon layui-icon-next"></i> ',
        elem: 'pages',
        limit: 20,
        count: num,
        jump: function (obj, first) {
            var idex = String(( - 1) * 20);
            $.post("/project/page/readAll", {
                    firstindex: obj.curr,
                    limt:obj.limit
                },
                function (data, status, xhr) {
                    $(".goods").empty();
                    var goodlist = JSON.parse(data);
                    for (var i = 0; i < goodlist.length; i++) {
                        var good = goodlist[i];
                        var a_good = document.createElement("div");
                        var goodbox = document.createElement("div");
                        var img = document.createElement("div");
                        var goodname = document.createElement("div");
                        var detil = document.createElement("div");
                        var price = document.createElement("div");
                        $(img).addClass("img");
                        $(img).css("background-image", "url('" + good.pictures + "main.jpg'");
                        $(goodname).addClass("goodname");
                        $(goodname).append(good.name);
                        $(detil).addClass("detil");
                        $(detil).append(good.detail);
                        $(price).addClass("price");
                        $(price).append("￥");
                        $(price).append(good.price);
                        $(goodbox).addClass("good-box");
                        $(goodbox).append(img);
                        $(goodbox).append(goodname);
                        $(goodbox).append(detil);
                        $(goodbox).append(price);
                        $(a_good).addClass("good");
                        $(a_good).append(goodbox);
                        $(a_good).attr("id", good.id);
                        $(a_good).click(function () {
                            location = "/project/good/readGood.action?id=" + $(a_good).attr('id');
                        });
                        $(".goods").append(a_good);
                    }
                }
            );
        }
    });
});
var offset = $(".nav>.login>div").offset();
var left = offset.left - 45
$(".nav+div").css("left", left + "px");
$(".nav+div .message").click(function () {
    location = "/message.html";
})
$(".serchbox button").click(function () {
    var str = $(".serchbox input[type='text']").val();
    if (str != "" && str != null) {
        $.post("/project/page/listNumber",
            {
                name: str,
                ListName: "search"
            },
            function (data, status, xhr) {
                layui.use('laypage', function () {
                    var laypage = layui.laypage;
                    laypage.render({
                        prev: '<i class="layui-icon layui-icon-prev"></i> ',
                        next: '<i class="layui-icon layui-icon-next"></i> ',
                        elem: 'pages',
                        limit: 20,
                        count: parseInt(data),
                        jump: function (obj, first) {
                            var index = String((obj.curr - 1) * 20);
                            $.post("/project/good/secrch", {
                                    firstpage: index,
                                    name: str
                                },
                                function (data, status, xhr) {
                                    $(".goods").empty();
                                    var goodlist = JSON.parse(data);
                                    for (var i = 0; i < goodlist.length; i++) {
                                        var good = goodlist[i];
                                        var a_good = document.createElement("div");
                                        var goodbox = document.createElement("div");
                                        var img = document.createElement("div");
                                        var goodname = document.createElement("div");
                                        var detil = document.createElement("div");
                                        var price = document.createElement("div");
                                        $(img).addClass("img");
                                        $(img).css("background-image", "url('" + good.pictures + "main.jpg'");
                                        $(goodname).addClass("goodname");
                                        $(goodname).append(good.name);
                                        $(detil).addClass("detil");
                                        $(detil).append(good.detail);
                                        $(price).addClass("price");
                                        $(price).append("￥");
                                        $(price).append(good.price);
                                        $(goodbox).addClass("good-box");
                                        $(goodbox).append(img);
                                        $(goodbox).append(goodname);
                                        $(goodbox).append(detil);
                                        $(goodbox).append(price);
                                        $(a_good).addClass("good");
                                        $(a_good).append(goodbox);
                                        $(a_good).attr("id", good.id);
                                        $(a_good).click(function () {
                                            location = "/project/good/readGood.action?id=" + $(a_good).attr('id');
                                        });
                                        $(".goods").append(a_good);
                                    }
                                }
                            );
                        }
                    });
                });
            })
    }
});
/*
$(selector).click(function (e) {
    location = "#";
    $(this).attr("goodid");
});*/