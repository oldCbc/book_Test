<%--
  Created by IntelliJ IDEA.
  User: CBC
  Date: 2020/11/16
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加图书</title>
</head>
<form action="bookServlet?" method="get" >
    <%--隐藏域的独特功能，多重赋值action--%>
    <input type="hidden" name="action" value="${empty param.id ? "add":"update"}">
    <%--
    回显id但是不能显示（自增不能修改），所以得隐藏；必须得有name属性值为id的，才能通过id进行修改
    --%>
    <input type="hidden" name="id" value="${requestScope.book.id}">
    <%--接收带过来的pageNo，执行完编辑后继续返回pageNo页--%>
    <input type="hidden" name="pageNo" value="${param.pageNo}">
<body>
    <table align="center">

            <tr>
                <td>姓名</td><td><input type="text" name="name" value="${requestScope.book.name}"></td>
            </tr>
            <tr>
                <td>作者</td><td><input type="text" name="author" value="${requestScope.book.author}"></td>
            </tr>
            <tr>
                <td>销量</td><td><input type="text" name="sales" value="${requestScope.book.sales}"></td>
            </tr>
            <tr>
                <td>库存</td><td><input type="text" name="store" value="${requestScope.book.store}"></td>
            </tr>
            <tr>
                <td>价格</td><td><input type="text" name="price" value="${requestScope.book.price}"></td>
            </tr>
            <tr>
                <td>图片</td><td><input type="text" name="img_path" value="${requestScope.book.img_path}"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" > </td>
                <%--
                是修改还是添加操作
                方案一：发起请求时，附加当前操作的方法参数，注入到隐藏域中
                都要传一个&method=add或method=update，然后这个隐藏域value="${param.method}"
                方案二：因为修改要传入参数id，所以可以判断参数id来确定
                value="${empty param.id ? "add":"update"}"
                方案三：判断Request域中是否有BookServlet修改图书信息的信息对象
                value="${empty bookServlet ? "add":"update"}"

                --%>
            </tr>
    </table>


</body>
</form>
</html>
