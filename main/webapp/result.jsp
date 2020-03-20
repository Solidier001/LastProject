<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.alipay.api.AlipayClient" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.alipay.api.request.AlipayTradePagePayRequest" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/12/25
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(application);
    AlipayClient alipayClient = (AlipayClient) wac.getBean("client");
    AlipayTradePagePayRequest alipayrequest= (AlipayTradePagePayRequest) request.getAttribute("alipayrequest");
    String result=alipayClient.pageExecute(alipayrequest).getBody();
    out.println(result);
%>
</div>
</body>
</html>
