<!-- <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/pay.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <title>Title</title>
</head>

<body>
    <div class="bar"></div>
    <div class="mycontainer">

        <div class="step">
            <div class="stepshow">
                <div class="steprow">
                    <div class="stepbox thispagebox">1</div>
                    <hr>
                    <div class="stepbox">2</div>
                    <hr>
                    <div class="stepbox">3</div>
                    <hr>
                    <div class="stepbox">4</div>
                </div>
                <div class="guiderow">
                    <div class="guiderbox">确认订单</div>
                    <div class="guiderbox">付款或确认付款方式</div>
                    <div class="guiderbox">确认收货</div>
                    <div class="guiderboxNomargin">评价</div>
                </div>
            </div>
        </div>
        <form action="/project/good/makeorder.action" method="post">
            <div class="ordertabletitle">确认交易地址</div>
            <ul></ul>
            <div class="ordertabletitle">确认订单详情信息</div>
            <div class="ordertable">

                <table>
                    <tr class="th">
                        <th>商品名称</th>
                        <th>单价</th>
                        <th>买家</th>
                        <th>卖家</th>
                        <th>数量</th>
                    </tr>
                    <tr>
                        <td colspan="5"></td>
                    </tr>
                    <tr class="td">
                        <td>
                            goodname
                        </td>
                        <td>
                            23
                        </td>
                        <td>
                            buyname
                        </td>
                        <td>
                            name
                        </td>
                        <td>1</td>
                    </tr>
                </table>
            </div>
            <div class="paybox">
                <div class="payrow">
                    <div class="price"></div>
                    <div class="cashsymblo">￥</div>
                    <div class="paylable">
                        实付款：
                    </div>
                </div>
                <label>交易地点:</label>
                <div class="location"></div>
                <br>
                <label>买家：</label>
                test
            </div>
            <button type="button" id="submitorder">提交订单</button>
        </form>
    </div>
    <dialog id="myDialog">
        <div class="menubar">
            <i class="fa fa-times fa-lg"></i>
        </div>
        <div class="container">
            <div>确认付款方式</div>
            <div>
                <select>
                    <option>在线支付</option>
                    <option>当面支付</option>
                </select>
            </div>
            <button type="button">确认</button>
        </div>
    </dialog>
    <script src="/js/pay.js"></script>
    <script>
        $.ajax({
            url: '/locations/',
            type: 'get',
            dataType: 'xml',
            success: function (data) {
                $(data).find("locations").find("location").each(function (index) {
                    var radioid = "loaction" + index;
                    var i = document.createElement("i");
                    var div = document.createElement("div");
                    var div2 = document.createElement("div");
                    var lable = document.createElement("label");
                    var li = document.createElement("li");
                    var radio = document.createElement("input");
                    radio.setAttribute("type", "radio");
                    radio.setAttribute("id", radioid);
                    radio.setAttribute("name", "location");
                    lable.innerText = $(this).text();
                    $(div).addClass("myradio");
                    $(i).addClass("fa").addClass("fa-map-marker").addClass("fa-2x");
                    div2.appendChild(i);
                    $(div2).append("交易地点");
                    $(div2).addClass("location");
                    $(div2).css("display", "inline-block");
                    $(lable).click(function () {
                        $(radio).click();
                        $("li").css("background-color", "transparent");
                        $("li").css("border", "0px");
                        $("li>.location").css("opacity", "0");
                        $(this).parent().css("background-color", "rgb(175, 252, 204)");
                        $(this).parent().css("border", "1px solid rgb(0, 255, 213)");
                        $(this).parent().find("div").eq(0).css("opacity", "1");
                        $(".paybox>.location").text($(this).text());
                        $(".paybox>.location").text($(this).text());
                    });
                    li.appendChild(div2);
                    li.appendChild(radio);
                    li.appendChild(div);
                    li.appendChild(lable);
                    $("ul").append(li);
                });
            }
        });
    </script>

</body>

</html> -->
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/1/13
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="/css/pay.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<body>
<div class="bar"></div>
<div class="mycontainer">

    <div class="step" >
        <div class="stepshow">
            <div class="steprow">
                <div class="stepbox thispagebox">1</div>
                <hr>
                <div class="stepbox">2</div>
                <hr>
                <div class="stepbox">3</div>
                <hr>
                <div class="stepbox">4</div>
            </div>
            <div class="guiderow">
                <div class="guiderbox">确认订单</div>
                <div class="guiderbox">付款或确认付款方式</div>
                <div class="guiderbox">确认收货</div>
                <div class="guiderboxNomargin">评价</div>
            </div>
        </div>
    </div>
    <form action="/project/account/buy" method="post">
        <div class="ordertabletitle">确认交易地址</div>
        <ul></ul>
        <div class="ordertabletitle">确认订单详情信息</div>
        <div class="ordertable">

            <table>
                <tr class="th">
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>买家</th>
                    <th>卖家</th>
                    <th>数量</th>
                </tr>
                <tr>
                    <td colspan="5"></td>
                </tr>
                <tr class="td">
                    <td>
                        <s:property value="#request.good.name"/>
                    </td>
                    <td>
                        <s:property value="#request.good.price"/>
                        <input type="hidden" name="goodid" value="<s:property value="#request.good.id" />">
                    </td>
                    <td>
                        <s:property value="#session.user.name"/>
                        <input type="hidden" name="id" value="<s:property value="#session.user.id" />">
                    </td>
                    <td>
                        <s:property value="#request.user.name"/>
                    </td>
                    <td>1</td>
                </tr>
            </table>
        </div>
        <div class="paybox">
            <div class="payrow">
                <div class="price"></div>
                <div class="cashsymblo">￥</div>
                <div class="paylable">
                    实付款：
                </div>
            </div>
            <label>交易地点:</label>
            <div class="location"></div>
            <br>
            <label>买家：</label>
            <s:property value="#request.user.nickname"/>
        </div>
        <button type="button"  id="submitorder">提交订单</button>
    </form>
</div>
    <dialog id="myDialog">
        <div class="menubar">
            <i class="fa fa-times fa-lg"></i>
        </div>
        <div class="container">
            <div>确认付款方式</div>
            <div>
                <select>
                    <option>在线支付</option>
                    <option>当面支付</option>
                </select>
            </div>
            <button type="button">确认</button>
        </div>
    </dialog>
<script src="/js/pay.js"></script>
<script>
    $.ajax({
        url: '/locations/<s:property value="#session.user.locations" />',
        type: 'get',
        dataType: 'xml',
        success: function (data) {
            $(data).find("locations").find("location").each(function (index) {
                var radioid = "loaction" + index;
                var i = document.createElement("i");
                var div = document.createElement("div");
                var div2 = document.createElement("div");
                var lable = document.createElement("label");
                var li = document.createElement("li");
                var radio = document.createElement("input");
                radio.setAttribute("type", "radio");
                radio.setAttribute("id", radioid);
                radio.setAttribute("name", "order.location");
                lable.innerText = $(this).text();
                $(div).addClass("myradio");
                $(i).addClass("fa").addClass("fa-map-marker").addClass("fa-2x");
                div2.appendChild(i);
                $(div2).append("交易地点");
                $(div2).addClass("location");
                $(div2).css("display", "inline-block");
                $(lable).click(function () {
                    $(radio).click();
                    $("li").css("background-color", "transparent");
                    $("li").css("border", "0px");
                    $("li>.location").css("opacity", "0");
                    $(this).parent().css("background-color", "rgb(175, 252, 204)");
                    $(this).parent().css("border", "1px solid rgb(0, 255, 213)");
                    $(this).parent().find("div").eq(0).css("opacity", "1");
                    $(".paybox>.location").text($(this).text());
                    $(".paybox>.location").text($(this).text());
                });
                li.appendChild(div2);
                li.appendChild(radio);
                li.appendChild(div);
                li.appendChild(lable);
                $("ul").append(li);
            });
        }
    });
</script>
</body>
</html>