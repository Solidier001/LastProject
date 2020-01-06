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
    <script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
    <script src="http://libs.cdnjs.net/jquery-validate/1.14.0/jquery.validate.min.js"></script>
    <script src="js/rigister.js"></script>
    <link rel="stylesheet" type="text/css" href="css/register.css">
    <title>Title</title>
</head>
<body>
    <form id="form" method="post" action="/project/account/rigister.action" >
        <table>
            <tr>
                <td >
                    <label for="name">
                    用户名<div class="alert">&nbsp;*</div>
                    </label>
                </td>
                <td><input type="text" name="name" id="name" required></td>
                <td class="alert"></td>
            </tr>
            <tr>
                <td >
                    <label for="password">
                        密码<div class="alert">&nbsp;*</div>
                    </label>
                </td>
                <td><input type="password" name="password" id="password" required></td>
                <td class="alert"></td>
            </tr>
            <tr>
                <td class="lable"> 
                    <label for="comfirmpassword">
                        确认密码<div class="alert">&nbsp;*</div>
                    </label>
                </td>
                <td><input type="password" name="comfirmpassword" id="comfirmpassword" equalTo="#password"></td>
                <td class="alert"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="notify">(含*必填)</div>
                </td> 
                
            </tr>
    </table>
        <input type="submit" id="button">
    </form>
</body>
</html>
