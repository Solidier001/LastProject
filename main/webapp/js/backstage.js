$(".nav>div").click(function (e) {
    $(".nav>div").removeClass("navafterclick");
    $(".nav>div").addClass("navbeforclick");
    $(this).removeClass("navbeforclick");
    $(this).addClass("navafterclick");
    $(".listafterclick").addClass("listbeforeclick");
    $(".listafterclick").removeClass("listafterclick");
    $($(this).attr("cid")).removeClass("listbeforeclick");
    $($(this).attr("cid")).addClass("listafterclick");
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
            , {field: 'times', title: '数量', sort: true}
            , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo', width: 184}
        ]]
    });
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        var tr = obj.tr;
        if (layEvent === 'detail') {
            layer.open({
                type: 2,
                title: '所有评论',
                shadeClose: true,
                shade: false,
                maxmin: false, //开启最大化最小化按钮
                area: ['400px', '500px'],
                content: '/showpage.html?id=' + data.id
            })
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.post("/project/good/delsteagood",
                    {
                        id: data.id
                    }, function (data, status, xhr) {
                        layer.alert(data);
                        if (data == "删除成功") {
                            obj.del();
                            layer.close(index);
                        }
                    })
            });
        } else if (layEvent === 'edit') {
            layer.open({
                type: 2,
                title: '修改商品数据',
                shadeClose: true,
                shade: false,
                maxmin: false, //开启最大化最小化按钮
                area: ['400px', '500px'],
                content: '/editgoods.jsp?id=' + data.id,
                cancel: function (index, layero) {
                    $.post("/project/page/onegood", {
                            id: data.id
                        },
                        function (data, textStatus, jqXHR) {
                            var good = JSON.parse(data);
                            obj.update({
                                id: good.id
                                , name: good.name
                                , price: good.price
                            });
                        }
                    )
                }
            })
        } else if (layEvent === 'LAYTABLE_TIPS') {

        }
    });
});
layui.use('table', function () {
    var table = layui.table;
    var h = parseInt($(".adminpanel").height()) - 100;
    table.render({
        elem: '#orderstable'
        , url: '/project/order/orderlist'
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
            , {field: 'buyrname', title: '买家'}
            , {field: 'name', title: '商品名'}
            , {field: 'price', title: '价格', sort: true}
            , {field: 'date', title: '订单日期', sort: true}
            , {field: 'nunber', title: '成交数量'}
            , {field: 'paymethod', title: '交易方式'}
            , {field: 'statu', title: '状态'}
            , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#tools'}
        ]]
    });
    table.on('tool(order)', function (obj) {
        var datas = obj.data;
        var layEvent = obj.event;
        var tr = obj.tr;
        if (layEvent === 'check') {
            $.post("/project/order/chick", {
                id: datas.id
            }, function () {
                $.post("/project/order/order", {
                    id: datas.id
                }, function (data) {
                    obj.update({
                        statu: data
                    });
                    $(tr).find("#check").attr("lay-event", "uncheck")
                    $(tr).find("#check").addClass("layui-btn-disabled")
                    $(tr).find("#check").text("已完成")
                })
            })
        }
    })
});
layui.use('table', function () {
    var table = layui.table;
    var h = parseInt($(".adminpanel").height()) - 100;
    table.render({
        elem: '#Orders'
        , url: '/project/order/BuyerOrderlist'
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
            , {field: 'ownername', title: '买家'}
            , {field: 'name', title: '商品名'}
            , {field: 'price', title: '价格', sort: true}
            , {field: 'date', title: '订单日期', sort: true}
            , {field: 'nunber', title: '成交数量'}
            , {field: 'paymethod', title: '交易方式'}
            , {field: 'statu', title: '状态'}
            , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#mytools'}
        ]]
    });
    table.on("tool(myorder)", function (obj) {
        var datas = obj.data;
        var layEvent = obj.event;
        var tr = obj.tr;
        if (layEvent == 'check') {
            $.post("/project/order/chick", {
                id: datas.id
            }, function () {
                $.post("/project/order/order", {
                    id: datas.id
                }, function (data) {
                    obj.update({
                        statu: data
                    });
                    $(tr).find("#check").attr("lay-event", "uncheck")
                    $(tr).find("#check").addClass("layui-btn-disabled")
                    $(tr).find("#check").text("已完成")
                })
            })
        }
        else if (layEvent == 'writereview') {
            layer.open({
                type: 2,
                title: '商品上架',
                shadeClose: true,
                shade: false,
                maxmin: false,
                area: ['400px', '500px'],
                content: '/revieworder.html?id=' + datas.id,
                index: 5
            })
        }
        else if (layEvent == 'myreview') {
            $.post('/project/order/readreview.action',
                {id: datas.id}, function (data) {
                    if (data != 'error') {
                        layer.open({
                            type: 2,
                            title: '商品上架',
                            shadeClose: true,
                            shade: false,
                            maxmin: false,
                            area: ['400px', '500px'],
                            content: '/review/ordersreview/' + data,
                            index: 5
                        })
                    }
                })

        }
    })
});
$(".plus>button").click(function (e) {
    layer.open({
        type: 2,
        title: '商品上架',
        shadeClose: true,
        shade: false,
        maxmin: false,
        area: ['400px', '500px'],
        content: '/upgoods.html',
        index: 5
    })
});