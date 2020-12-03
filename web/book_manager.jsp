<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CBC
  Date: 2020/11/16
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理系统</title>
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
<%--    <script type="text/javascript">
        $(function (){
            $(".deleteClass").click(function (){
                alert("确定要删除?");
            })
        })
    </script>--%>
    <script src="jquery-3.1.1.min.js"></script>
    <script type="text/javascript" >
        $(function (){
            $(".deleteClass").click(function (){
                /**
                 * 两个按钮，一个true提交，false取消
                 * 一定要return！！！！！！！！！！！！！！！！！！！！！！！！！！！confirm+
                 * 在事件的function函数中，有一个this对象，是正在响应的dom对象,通过两次父元素后找到tr，然后查找第一个子元素的兄弟元素的文本内容
                 *
                 */
                //alert("确定要删除?");
                return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").next().text()+"】吗？");
            /*    return false;//阻止元素的默认行为，如果confirm 返回true，则此段不执行，否则此段执行*/

            })
        })
    </script>
</head>

<body background="regist_background.jpg">

<table class="hovertable" style="height:500px;width: 1000px;font-size: 18px;table-layout: fixed" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>作者</th>
        <th>销量</th>
        <th>库存</th>
        <th>价格</th>
        <th colspan="2">图片</th>
        <th colspan="2">操作</th>

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
        <td colspan="2"><img src="${book.img_path}" style="width:170px;height:60px"></td>
        <td><a href="bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
        <td><a  class="deleteClass" href="bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a> </td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="10" align="right"><a href="book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a>
        </td>
<%--"bookServlet?action=add"--%>
    </tr>
</table>
<%--静态包含，更改上方ip地址--%>
<%@include file="/common/page_nav.jsp"%>
<%@include file="/common/head.jsp"%>
</body>
</html>
