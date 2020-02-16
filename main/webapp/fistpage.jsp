<%--<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="/css/fistpage.css">
    <link rel="stylesheet" href="/css/global.css" />
    <script src="/layui/layui.js"></script>
    <title>fistpage</title>
</head>

<body>
    <div id="bar">
        <iframe src="/bar.html"></iframe>
    </div>
    <div class="serch-box">
        <form method="get" action="/project/good/secrch.action">
            <div class="text-box">
                <input type="text" name="name">
            </div>
            <button class="button">
                搜索
            </button>
        </form>
    </div>
    <div class="mycontainer">
        <div id="tittle">食品</div>
        <ul></ul>
        <div>
            <!-- <div id="pagecount"></div> -->
        </div>
    </div>
    <script src="/js/fistpage.js"></script>
</body>

</html>--%>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/1/5
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="daomain.Goods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="/js/fistpage.js"></script>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/fistpage.css">
    <link rel="stylesheet" href="/css/global.css" />
    <title>fistpage</title>
</head>
<body>
<div id="bar">
    <iframe src="/bar.html"></iframe>
</div>
<div class="serch-box">
    <form method="get" action="/project/good/secrch.action">
        <div class="text-box">
            <input type="text" name="name">
        </div>
        <button class="button">
            搜索
        </button>
    </form>
</div>
<div class="container">
    <div id="tittle">食品</div>
    <ul>
        <s:iterator value="#request.allgoods" var="goods">
            <li class="content-box">
                <div class="li-box">
                    <a href="/project/good/readGood.action?id=<s:property value="#goods.id"/>">
                        <img src="<s:property value="#goods.pictures"/>/main.png">
                        <div class="price"><s:property value="#goods.price"/>￥</div>
                        <div class="describe"><s:property value="#goods.name"/>:<s:property value="#goods.Goods"/></div>
                    </a>
                </div>
            </li>
        </s:iterator>
    </ul>
</div>
</body>
</html>
