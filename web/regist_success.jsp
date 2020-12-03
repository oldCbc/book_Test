<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面成功</title>
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
<!--    <img class="logo_img" alt="" src="static/img/logo.gif" >-->
    <span class="wel_word"></span>

</div>

<div id="main">

    <h1>注册成功! <a href="index.jsp">转回首页</a></h1>
</div>
<div>
   <h2><a href="login_success.jsp" >前往主页</a></h2>
</div>
<%--静态包含共用代码--%>
<%@include file="/common/footer.jsp"%>


</body>
</html>