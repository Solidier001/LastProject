<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/1/3
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form enctype="multipart/form-data" action="/project/good/upgood.action" method="post">
    <table>
        <tr>
            <td>商品名称</td>
            <td><input type="text" name="name" required></td>
        </tr>
        <tr>
            <td>价格</td>
            <td><input type="number" name="price" required></td>
        </tr>
        <tr>
            <td>详情</td>
            <td>
                <textarea maxlength="450000" cols="200" name="detail" required></textarea>
            </td>
        </tr>
        <tr>
            <td>图片</td>
            <td><input type="file" name="image"required></td>
        </tr>
    </table>
    <input type="submit">
</form>
</body>
</html>
