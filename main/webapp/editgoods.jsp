<%@ page import="daomain.Goods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="/css/editgoods.css"/>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
<div class="cntentbox">
        <form action="/project/good/uodate" method="post">
            <table>
                <tr>
                    <td>ID</td>
                    <td>
                        <input name="id" type="text"
                        <s:if test="#request.good!=null">
                                value="<s:property value="#request.good.id"/>"
                        </s:if> readonly>
                    </td>
                </tr>
                <tr>
                    <td>名称</td>
                    <td><input type="text"  name="name"
                    <s:if test="#request.name!=null">
                               value="<s:property value="#request.good.name"/>"
                               </s:if> required></td>
                </tr>
                <tr>
                    <td>详情</td>
                    <td><textarea maxlength="450000" name="detail"  required>
                        <s:if test="#request.good!=null">
                            <s:property value="#request.good.id"/>
                        </s:if>
                    </textarea></td>
                </tr>
                <tr>
                    <td>个数</td>
                    <td><input type="number" name="times"
                    <s:if test="#request.good!=null">
                               value="<s:property value="#request.good.times"/>"
                    </s:if> required></td>
                </tr>
                <tr>
                    <td>价格</td>
                    <td><input type="number" name="price"
                    <s:if test="#request.good!=null">
                               value="<s:property value="#request.good.price"/>"
                    </s:if> required></td>
                </tr>
            </table>
            <input type="submit">
        </form>
    </div>
    <script src="/js/editgoods.js"></script>
</body>
</html>