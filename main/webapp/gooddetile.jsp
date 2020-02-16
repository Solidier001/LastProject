<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/1/5
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <link rel="stylesheet" href="/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="/css/gooddeteile.css" />
  <link rel="stylesheet" href="/css/global.css" />
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
          integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
  <title>Document</title>
  <title>Title</title>
</head>

<body>
<div id="bar">
  <iframe src="/bar.html"></iframe>
</div>
<div class="banner"></div>
<div class="layui-fluid">
  <div class="layui-row">
    <div class="layui-col-md5 layui-col-md-offset1">
      <div class="layui-carousel" id="test1">
        <div carousel-item>
          <s:iterator value="#request.pictures" var="oneimg">
            <div class="img"
               style="background-image: url('<s:property value="#oneimg"/>');">
            </div>
          </s:iterator>
        </div>
      </div>
    </div>
    <div class="layui-col-md4">
      <div class="show">
        <div class="gooddetile">
          <div class="good-name"><s:property value="#request.sample.name"/></div>
          <div class="good-price">
            <div class="lable">转&nbsp;卖&nbsp;价:</div>
            <b>¥</b>
            <div class="price"><s:property value="#request.sample.price"/></div>
          </div>
          <hr>
          <div class="lable">新&nbsp;旧&nbsp;度:</div>
          <div class="lable">交易方式:</div>
          <div class="lable">简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;介:</div>
          <div class="detile"><s:property value="#request.sample.detail"/></div>
        </div>
        <div class="buy">
          <button id="buy" g_id="<s:property value="#request.sample.id"/>">立即购买</button>
          <button id="talk">联系卖家</button>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="/layui/layui.js"></script>
<script src="/js/gooddetile.js"></script>
</body>

</html>
