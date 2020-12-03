<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CBC
  Date: 2020/11/19
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <Base href="http://localhost:8080/book_Test/">
    <title>购物车</title>
    <script src="jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        $(function (){
            $("a.deleteItem").click(function (){
                return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").next().text()+"】吗?");
            })
            $("#clear").click(function (){
                return confirm("你确定要清空购物车吗o(╥﹏╥)o？")
            })
            $(".updateCount").change(function (){
                var name=$(this).parent().parent().find("td:first").next().text();
                var count=this.value;
                var id=$(this).attr("bookid");
                if(confirm("你确定要将【"+name+"】的数量修改为【"+count+"】吗？")){
                    //发送请求给服务器保存
                    location.href="cartServlet?action=updateCount&count="+count+"&id="+id;
                }else {
                    //如果取消，则defaultvalue是默认值，他会记录上一次的结果
                    this.value=this.defaultValue;
                }
            })
        })
    </script>
    <style>
        table{
            border-collapse: collapse;
            width: 500px;
            border: 1px red solid;
        }
        th,td{
            border-collapse: collapse;
            color: blue;
            border: 1px red solid;
        }
    </style>
</head>
<body background="lang.jpg">
<div align="center">
    <div>
        <table>
            <tr>
                <td>书籍编号</td>
                <td>书籍名称</td>
                <td>书籍数量</td>
                <td>书籍价格</td>
                <td>书籍总价</td>
                <td>删除此书</td>
            </tr>
            <c:if test="${empty sessionScope.cart.items}">
                <tr>
                    <td colspan="6" align="center" style="color: #ff4800"><a href="index.jsp"> 亲，购物车空空如也……不如去剁手？</a></td>
                </tr>
            </c:if>
            <c:if test="${not empty sessionScope.cart}">
                <c:forEach items="${sessionScope.cart.items}" var="entry">
                    <tr>
                        <td>${entry.value.id}</td>
                        <td>${entry.value.name}</td>
                        <td>
                        <input type="text" bookId="${entry.value.id}" class="updateCount" value="${entry.value.count}" style="width: 40px">
                        </td>
                        <td>${entry.value.price}</td>
                        <td>${entry.value.totalPrice}</td>
                        <td><a href="cartServlet?action=deleteItem&id=${entry.value.id}" class="deleteItem">删除</a></td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    <c:if test="${not empty sessionScope.cart.items}">
    <div>
        共 ${sessionScope.cart.totalCount}本书
        合计 ${sessionScope.cart.totalPrice}元
        <div>
            <a href="cartServlet?action=clear" id="clear">清空购物车</a>
<%--注意了！！！！！！！！！！！！！！！！！！button不能跳转到类名！！！！！！！！！！！！
            <input type="button" id="clear" href="cartServlet?action=clear" value="清空购物车">
--%>
           <a href="orderServlet?action=createOrder">去结账</a>

        </div>
    </div>
    </c:if>
    <div>
        <a href="index.jsp">返回商品页面</a>
<%--        <input type="button" href="index.jsp" value="返回商品页面">--%>
    </div>
</div>

</body>
</html>
