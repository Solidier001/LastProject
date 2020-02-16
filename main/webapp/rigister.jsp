<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/12/27
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建账户</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="/css/register.css">
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>

<body>
<div id="regiter-form">
    <div class="title">创建您的帐户</div>
    <form class="layui-form" action="/project/account/rigister.action">
        <div class="layui-form-item">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-block">
                <input type="text" name="nickname" required lay-verify="required" placeholder="昵称" autocomplete="off"
                       class="input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required|name" placeholder="请输入姓名"
                       autocomplete="off" class="input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="input">
                <i class="layui-icon layui-icon-password"></i>
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-inline">
                <input type="password" name="comfirmpassword" lay-verify="confirmPass" placeholder="确认密码"
                       autocomplete="off" class="input">
                <i class="layui-icon layui-icon-password"></i>
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">院系</label>
            <div class="layui-input-inline">
                <select name="quiz1">
                    <option value="">请选择省</option>
                    <option value="浙江" selected="">浙江省</option>
                    <option value="你的工号">江西省</option>
                    <option value="你最喜欢的老师">福建省</option>
                </select>
            </div>
            <label class="layui-form-label">专业</label>
            <div class="layui-input-inline">
                <select name="quiz2">
                    <option value="">请选择市</option>
                    <option value="杭州">杭州</option>
                    <option value="宁波" disabled="">宁波</option>
                    <option value="温州">温州</option>
                    <option value="温州">台州</option>
                    <option value="温州">绍兴</option>
                </select>
            </div>
            <label class="layui-form-label">班级</label>
            <div class="layui-input-inline">
                <select name="quiz3">
                    <option value="">请选择县/区</option>
                    <option value="西湖区">西湖区</option>
                    <option value="余杭区">余杭区</option>
                    <option value="拱墅区">临安市</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男">
                <input type="radio" name="sex" value="女" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="/layui/layui.js" charset="utf-8"></script>
<script src="/js/rigister.js"></script>
</body>

</html>