<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录成功</title>
    <style type="text/css">

        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }
        h2 {
            text-align: center;
            margin-top: 200px;
        }

        h2 a {
            color:red;
        }

    </style>
</head>
<body background="regist_background.jpg">
<div id="header">
<!--    <img class="logo_img" alt="" src="../../static/img/logo.gif" >-->


</div>
<div align="right" id="d0" >

    <a href="manager.jsp" >管理数据</a>
</div>
<div align="right">
    <a href="http://localhost:8080/book_Test/userServlet?action=logout">注销</a>
</div>
<div id="main">

    <h1>欢迎${sessionScope.user.username}回来 <a href="index.jsp">转到首页</a></h1>
<!--    <h2>查询信息<a href="/query">查询</a> </h2>-->
    <form action="http://localhost:8080/book_Test/query" method="get" name="form1" >
        <table align="center">
        <tr><td><input type="text" name="username" id="username">输入用户名查询信息</td></tr>
        <tr><td><input type="submit"></td></tr>
        </table>
    </form>

    <br>
    <br>
    <br>
    <form method="get" action="http://localhost:8080/book_Test/downloadServlet" name="form3">
        <div>
            <img src="lang.jpg" width="150" height="100">
        </div>

        <input type="submit" value="下载图片">
    </form>

</div>

<div >
    <form action="http://localhost:8080/book_Test/uploadServlet" method="post" enctype="multipart/form-data" name="form2">
        用户名<input type="text" name="username1"><br>
        头像<input type="file" name="photo"><br>
        <input type="submit" name="上传">
    </form>
</div>

<%--静态包含共用代码--%>
<%@include file="/common/footer.jsp"%>


</body>
</html>