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
        location="/project/good/makeorder.action?id="+$(this).attr("g_id");
    }
)