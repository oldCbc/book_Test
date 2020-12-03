<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎光临首页！</title>
    <script src="jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        $(function (){

            //加入购物车事件
            $("button.addToCart").click(function (){
                if(${empty sessionScope.user}){
                    location.href="http://localhost:8080/book_Test/login.jsp";
                }else {
                    //将this对象转为jQuery对象调用attr方法获取指定属性的值
                    var bookId = $(this).attr("bookId");
                    //在事件中的location.href方法直接转向地址
                    // location.href="cartServlet?action=addItem&id="+bookId;
                    $.getJSON("cartServlet", "action=ajaxAddItem&id=" + bookId, function (data) {
                        $("#totalCount").text("您的购物车有【" + data.totalCount + "】本书，");
                        $("#lastName").text("您刚刚把【" + data.lastName + "】放入了购物车");
                    })
                }
            })
        })

    </script>

    <style type="text/css">
        table.hovertable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #999999;
            border-collapse: collapse;
        }
        table.hovertable th {
            background-color:#c3dde0;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
        table.hovertable tr {
            background-color:#d4e3e5;
        }
        table.hovertable td {
            border-width: 2px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
    </style>
</head>
<body background="regist_background.jpg" >
<div style="top: 0px;position: fixed;align-self: center">
    <%--如果用户还没有登陆，显示【登录和注册】--%>
    <c:if test="${empty sessionScope.user}">
        <h1>欢迎进入书城！</h1>
        <h3><a href="login.jsp">登录</a> </h3>
        <h3><a href="regist_static.jsp">注册</a> </h3>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <h1>欢迎${sessionScope.user.username}进入书城！<br></h1>
        <h2><a href="login_success.jsp">进入主页</a></h2>
        <h2><a href="">我的订单</a> </h2>
        <h2><a href="userServlet?action=logout">注销</a> </h2>
        <h2><a href="cart/cart.jsp">购物车</a> </h2>
        <div style="color: darkblue" >
            <c:if test="${empty sessionScope.cart.items}">
                <span id="totalCount" ></span><br>
                <span id="lastName" >购物车空空如也~~~~</span>
            </c:if>
            <c:if test="${not empty sessionScope.cart.items}">
                <span id="totalCount" >您的购物车有【${sessionScope.cart.totalCount}】本书，</span><br>
                <span id="lastName" >您刚刚把【${sessionScope.lastName}】放入了购物车</span>

                <%--获取bookId的值，来确定book的信息，取出book的name属性值就是lastname--%>
                <%--${sessionScope.cart.items.${param.id}.id}--%>

            </c:if>
<%--            <c:if test="${empty sessionScope.cart.items}">--%>
<%--                <span id="totalCount"></span>--%>
<%--                <span id="lastName">购物车空空如也~~~~</span>--%>
<%--            </c:if>--%>
<%--            <c:if test="${not empty sessionScope.cart.items}">--%>
<%--                <span id="totalCount" >您的购物车有【${sessionScope.cart.totalCount}】本书，</span>--%>
<%--                <span id="lastName" >您刚刚把【${sessionScope.lastName}】放入了购物车</span>--%>
<%--            </c:if>--%>

        </div>

    </c:if>


</div>
    <form action="client/clientBookServlet" method="get">
        <input type="hidden" name="action" value="pageByPrice">
    <div align="center">
        价格：<input type="text" name="min" id="min" value="${param.min}" style="width: 40px">元
             <input type="text" name="max" id="max" value="${param.max} " style="width:40px">元
             <input type="submit" value="查询">
    </div>
    </form>
<div style="top: 0px" >
    <table align="center" class="hovertable" style="height:300px;width: 800px;font-size: 18px;table-layout: fixed">
        <tr>
            <th width="20px">编号</th>
            <th>姓名</th>
            <th>作者</th>
            <th>销量</th>
            <th>库存</th>
            <th>价格</th>
            <th>图片</th>
            <th>操作</th>

        </tr>
        <%--这不能全部查询了，得分页查询当前的数据并遍历--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.store}</td>
                <td>${book.price}</td>
                <td><img src="${book.img_path}" style="width:80px;height:60px"></td>
                <td><button id="addToCart"  name="addToCart" bookId="${book.id}" class="addToCart">加入购物车</button></td>
            </tr>
        </c:forEach>
        <tr>

        </tr>
    </table>
</div>
<Base href="http://localhost:8080/book_Test/">
<%@include file="/common/page_nav.jsp"%>
</body>
</html>