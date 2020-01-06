<%@ page import="java.util.ArrayList" %>
<%@ page import="daomain.Goods" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/1/5
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>fistpage</title>
</head>
<body>
<table>
    <s:iterator value="#request.allgoods" var="goods">
        <tr>
            <td><a href="/project/good/readGood.action?id=<s:property value="#goods.id"/>">
                <s:property value="#goods.name"/></a>
            </td>
        </tr>
    </s:iterator>
 </table>
</body>
</html>
