$(".nav>div").click(function (e) {
    $(".nav>div").removeClass("navafterclick");
    $(".nav>div").addClass("navbeforclick");
    $(this).removeClass("navbeforclick");
    $(this).addClass("navafterclick");
});
$(".menulist>div").click(function (e) {
    $(".menulist>div").removeClass("menulistafterclick");
    $(this).addClass("menulistafterclick");
    $(".adminpanel>div").removeClass("adminpanelafterclick");
    $($(this).attr("cid")).addClass("adminpanelafterclick");
});

layui.use('table', function () {
    var table = layui.table;
    var h = parseInt($(".adminpanel").height()) - 100;
    table.render({
        elem: '#goodstable'
        , url: '/project/page/allGoodsTable'
        , page: true
        , height: h
        , limit: 20
        , loading: true
        , request: {
            pageName: 'firstindex',
            limitName: 'limt'
        }
        , cols: [[
            {field: 'id', title: 'ID', sort: true, fixed: 'left'}
            , {field: 'name', title: '商品名'}
            , {field: 'price', title: '价格', sort: true}
            , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo'}
        ]]
    });
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        var tr = obj.tr;
        if (layEvent === 'detail') {
            //do somehing
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.post("/project/good/delsteagood",
                    {
                        id: data.id
                    }, function (data, status, xhr) {
                        layer.alert(data);
                        if (data=="删除成功") {
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);
                        }
                    })
            });
        } else if (layEvent === 'edit') { //编辑
            //do something

            //同步更新缓存对应的值
            obj.update({
                username: '123'
                , title: 'xxx'
            });
        } else if (layEvent === 'LAYTABLE_TIPS') {
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }
    });
});
$(".plus>button").click(function (e) { 
    layer.open({
        type: 2,
        title: '商品上架',
        shadeClose: true,
        shade: false,
        maxmin: false, //开启最大化最小化按钮
        area: ['400px', '500px'],
        content: '/upgoods.html',
        index :5
    })
});