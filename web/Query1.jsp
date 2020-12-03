<%@ page import="java.util.List" %>
<%@ page import="com.book1.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: CBC
  Date: 2020/11/14
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询信息</title>
<%--    <script>
        //获取后端设置的值
        var role="<%=request.getAttribute("key")%>";
    </script>--%>
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
<%--    <script>
        alert(1+1)
    </script>--%>
</head>
<body background="regist_background.jpg">
        <%
            //遍历域数据中的集合
            List<User> userlist=(List<User>)request.getAttribute("key");
        %>
<form method="get" action="jquery">
    <table >
        <tr>
            <td>ID</td>
            <td>姓名</td>
            <td>密码</td>
            <td>邮箱</td>
        </tr>
            <%for(User user:userlist){%>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getUsername()%></td>
            <td><%=user.getPassword()%></td>
            <td><%=user.getEmail()%></td>
        </tr>
        <%}%>
    </table>

</form>
        <a href="login_success.jsp">返回主页</a>
        <%--静态包含共用代码--%>
        <%@include file="/common/footer.jsp"%>

</body>
</html>
