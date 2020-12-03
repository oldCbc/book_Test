<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
<%--
    <style>
        table{
            border-collapse: collapse;
        }
        table,tr,td{
            border: 1px solid #ff4800;

        }
        td{
            width: 100px;
            height: 50px;
            text-align: center;
        }
        .errorMsg{
            /* display: none;*/
            text-align: right;
            color: #ff4800;
            font-size: 50px;

        }
    </style>--%>
    <style>
        body{
            margin:0;
            padding:0;
            background:#487eb0;
        }
        .signup-form{
            width:300px;
            padding:20px;
            text-align: center;
            /*        background: url("./static/jpg/02.png");*/
            position: absolute;
            top:50%;
            left:50%;
            transform:translate(-50%,-50%);
            overflow:hidden;
        }
        .sign-form h1{
            margin-top: 100px;
            font-family: 'Permanent Marker',cursive;
            font-size: 40px;
        }
        .signup-form input {
            display: block;
            width: 100%;
            padding:0 16px;
            height:44px;
            text-align: center;
            box-sizing: border-box;
            outline: none;
            border: none;
            font-family: "montserrat",sans-serif;
        }
        .txtb{
            margin:20px 0;
            background: rgba(255,255,255,.5);
            border-radius: 6px;
        }
        .signup-btn{
            margin-top: 60px;
            margin-bottom: 20px;
            background: #487eb0;
            color:white;
            border-radius: 44px;
            cursor: pointer;
            transition:0.8s;
        }
        .signup-btn:hover{
            transform:scale(0.96);
            background: rgba(0,0,0.3);
        }
        .signup-form a{
            text-decoration: none;
            color:white;
            font-family: "montserrat",sans-serif;
            font-size: 14px;
            padding: 10px;
            transition:0.8s;
            display: block;
            border-radius: 44px;
        }
        .signup-form a:hover{
            transform:scale(0.96);
            background: rgba(0,0,0.3);
        }
    </style>
    <script src="jquery-3.1.1.min.js"></script>
    <script>
        $(function (){
            var movalue=$("#username").val();
            //   alert(movalue);
            /*   $("#username").click(function (){
                   $(this).val("");
               })*/
            $("#username").focus(function () {
                if (this.value==movalue) {

                    //       this.value("");
                    $(this).val("");
                }
            })
            $("#username").blur(function (){
                var a="";
                if(this.value==a){
                    $(this).val(movalue);
                }

            })

            /*//通过JSON动态获取失去焦点判断用户名是否存在
            $("#username").blur(function (){
                //获取用户名信息
                var username=this.value;
                //获取服务器的确认信息
                $.getJSON("http://localhost:8080/book_Test/userServlet","action=ajaxExistUsername&username="+username,function (data){
                    if(data.existsUsername){
                        $("#error").text("用户名不可用");
                    }else {
                        $("#error").text("用户名可用!");
                    }
                })
            })*/
        })
    </script>
</head>
<body background="regist_background.jpg">
<div class="signup-form">
<form action="userServlet" method="post">
    <%--隐藏域，使UserServlet能够调取--%>
    <input type="hidden" name="action" value="login">

        <h1>Sign Up</h1>
         <input type="text" id="username" name="username"  class="txtb" placeholder="用户名"
                                   value="${requestScope.username}">
            <!-- value="请输入用户名"-->

         <input type="password" id="password" name="password"  placeholder="密码"  class="txtb">
            <!--value="请输入密码"-->
       <%-- <tr>
            验证码<input type="text" name="code" style="width: 50px;height: 20px" value="">
            <img src="http://localhost:8080/book_Test/Kaptcha" style="width: 80px;height: 30px">
        </tr>--%>

 <%--           <td colspan="2" align="center" id=" login">--%>
        <input type="submit" id="sub" value="登录" class="signup-btn">
        <a href="regist_static.jsp">创建用户</a>


</form>
</div>
<div id="error" align="right">

  <%--  &lt;%&ndash;因为第一次的时候request域中无数据，为null&ndash;%&gt;
    <%=request.getAttribute("msg")==null?"请输入用户名和密码！":request.getAttribute("msg")%>--%>
    ${empty requestScope.msg?"请输入用户名和密码!":requestScope.msg}
</div >
<%--静态包含共用代码--%>

<%@include file="/common/footer.jsp"%>
</body>
</html>