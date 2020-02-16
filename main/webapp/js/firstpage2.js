$.post("/project/page/listNumber",
    {ListName: "all"},
    function (data, status, xhr) {
        var num = parseInt(parseInt(data) / 20);
        if (parseInt(data) % 20 != 0) {
            num++;
        }
        sessionStorage.num=num;
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
        elem: 'pages',
        limits: 20,
        count: sessionStorage.num,
        jump: function (obj, first) {
            var idex = String((obj.curr - 1) * 20);
            $.post("/project/page/readAll", {
                    firstindex: idex
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
                        $(img).css("background-image", "url('" + good.pictures + "/main.png'");
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
                            location = "/project/good/readGood.action?id=$(a_good).attr('id')";
                        });
                        $(".goods").append(a_good);
                    }
                }
            );
        }
    });
});/*
$(selector).click(function (e) {
    location = "#";
    $(this).attr("goodid");
});*/