<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <!--写base标签永远固定相对路径的参照，本页面所有的路径都参照此路径-->
<!--    <base href="http://localhost:8080/book_Test/">-->

<%--/*        table{
            border-collapse: collapse;
        }
        table,tr,td{
            border: 1px solid #ff4800;

        }
        td{
            width: 100px;
            height: 50px;
            text-align: center;
        }*/--%>
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
        /*background: url("./static/jpg/02.png");*/
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
     .errorMsg{
                 /* display: none;*/
                 text-align: center;
                 color: #ff4800;
                 font-size: 20px;

             }
    </style>
    <script src="jquery-3.1.1.min.js"></script>
    <%--<script>  失去焦点！
        //一个聚焦，一个失去焦点；默认值
     //   alert($);
        var a="";
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
        })
        $(function (){
            var movalue1=$("#password").val();
            $("#password").focus(function () {
                if (this.value==movalue1) {

             //       this.value("");
                    $(this).val("");
                }
            })
                $("#password").blur(function (){
                if(this.value==a){
                    $(this).val(movalue1);
                }

            })
        })
        $(function (){
            var movalue3=$("#email").val();
            $("#email").focus(function () {
                if (this.value==movalue3) {

             //       this.value("");
                    $(this).val("");
                }
            })
                $("#email").blur(function (){
                if(this.value==a){
                    $(this).val(movalue3);
                }

            })
        })
        $(function (){
            var movalue4=$("#password1").val();
            $("#password1").focus(function () {
                if (this.value==movalue4) {

             //       this.value("");
                    $(this).val("");
                }
            })
                $("#password1").blur(function (){
                if(this.value==a){
                    $(this).val(movalue4);
                }

            })
        })
        $(function (){
            var movalue5=$("#code").val();
            $("#code").focus(function () {
                if (this.value==movalue5) {

             //       this.value("");
                    $(this).val("");
                }
            })
                $("#code").blur(function (){
                if(this.value==a){
                    $(this).val(movalue5);
                }

            })
        })

    </script>--%>
    <script>
        var a="";
        $(function (){
            var movalue3=$("#email").val();
            $("#email").focus(function () {
                if (this.value==movalue3) {

                    //       this.value("");
                    $(this).val("");
                }
            })
            $("#email").blur(function (){
                if(this.value==a){
                    $(this).val(movalue3);
                }

            })

            //通过JSON动态获取失去焦点判断用户名是否存在
            $("#username").blur(function (){
                //获取用户名信息
                var username=this.value;
                //获取服务器的确认信息
                $.getJSON("http://localhost:8080/book_Test/userServlet","action=ajaxExistUsername&username="+username,function (data){
                    if(data.existsUsername){
                        $("#errorMsg").text("用户名不可用");
                    }else {
                        $("#errorMsg").text("用户名可用!");
                    }
                })
            })
        })
    </script>
    <script>
        $(function (){
            $("#img_code").click(function (){
                //在单击事件中，有一个this对象，是当前正在响应的dom对象
                this.src="http://localhost:8080/book_Test/Kaptcha?&"+new Date();
            })
            /* 验证用户名，必须由数字和下划线及字母做成,5-12位
            *1、获取用户名输入框的内容
            *2、创建正则表达式
            *3、使用test方法验证
            * 4、提示结果
            */
            $("#sub").click(function (){
                //用户名
                var usernameText=$("#username").val();
                var usernamePatt=/^\w{5,12}$/;
                if(!usernamePatt.test(usernameText)){
                    $("div.errorMsg").text("用户名不合法！");
                    /*return false表示阻止当前行为，在这表示阻止提交表单*/
                    return false;
                }
                //密码
                var passwordText=$("#password").val();
                var passwordPatt=/^\w{5,12}$/;
                if(!passwordPatt.test(passwordText)){
                    $("div.errorMsg").text("密码不合法！");
                    return false;
                }
                //确认密码
                var repwdText=$("#password1").val();
                if(repwdText!==passwordText){
                    $("div.errorMsg").text("密码不一致！");
                    return false;
                }
                //邮箱
                var emailText=$("#email").val();
                var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if(!emailPatt.test(emailText)){
                    $("div.errorMsg").text("邮箱格式不合法！");
                    return false;
                }
                //验证码
                var codeText=$("#code").val();
                var codeText=$.trim(codeText);
                if(codeText==null||codeText==""){
                    $("div.errorMsg").text("验证码不能为空！");
                    return false;
                }
                //如果全部正确则清除内容（防止以下情况:※当第一次输入用户名或其他的不合法时，已经在errorMsg赋值了，第二次往后里边一直有数据
                // 所以当合法时要清除任何内容）
                //若果以上都没问题，那么不能显示任何内容【包括以前提示的错误信息擦除】
                $("div.errorMsg").text("");
            })
        })
       //////////////////////////验证密码邮箱……

    </script>
</head>

<body background="regist_background.jpg">
<div class="signup-form">
<form action="userServlet" method="post">
    <%--隐藏域--%>
    <input type="hidden" name="action" value="regist">

    <h1 align="center">Regist</h1>

    <div class="errorMsg" id="errorMsg">
   <%--     <%=request.getAttribute("msg")==null?"请注册！":request.getAttribute("msg")%>--%>
<%--        ${empty requestScope.msg?"请注册！":requestScope.msg}--%>

    </div>

   
        
            <input type="text" placeholder="用户名" id="username" name="username" value="${requestScope.username}" class="txtb">
        
        
            <input type="password" placeholder="密码" id="password" name="password"  class="txtb">
        
        
            <input type="password" placeholder="确认密码" id="password1" name="password1"  class="txtb">
        
        
             <input type="text" placeholder="邮箱地址" id="email" name="email" value="请输入邮箱地址"  class="txtb">
        
             <%--  
            验证码 <input type="text" id="code" name="code"  class="align">

            <!--value="请输入验证码"-->
            --%>

            <img src="http://localhost:8080/book_Test/Kaptcha" id="img_code" style="width: 180px;height: 40px" alt="未显示？点击换一张">
             

         

            <input type="text" placeholder="验证码"  id="code" name="code"  value="" class="txtb">

            <input type="submit"  value="注册" class="signup-btn">
            <a href="login.jsp" class="signup-btn">登录</a>

</form>
</div>
<%--静态包含共用代码--%>
<%--
<%@include file="/common/footer.jsp"%>--%>

</body>
</html>