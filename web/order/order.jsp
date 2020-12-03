<%--
  Created by IntelliJ IDEA.
  User: CBC
  Date: 2020/11/19
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结算</title>
    <Base href="http://localhost:8080/book_Test/">
</head>
<body background="lang.jpg">
<div>
    <a href="cart/cart.jsp">返回购物车</a>
</div>
<div style="color: #ff4800">
    您的订单已提交，订单号为：${sessionScope.orderId},请保存！
</div>

</body>
</html>
