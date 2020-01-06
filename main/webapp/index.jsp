<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        td {
            align-content: center;
        }
    </style>
</head>
<body>
<form method="post" action="/project/account/login.action">
    <table>
        <tr>
            <td><label for="id">账号</label></td>
            <td><input type="text" name="id" id="id"></td>
        </tr>
        <tr>
            <td><label for="password">密码</label></td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" id="button" style="float: right;"></td>
        </tr>
    </table>
    <a href="rigister.jsp">注册</a>
</form>
</body>
</html>
