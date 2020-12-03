
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>高大上登录</title>
</head>
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
</style>
<body>
<div class="signup-form">
    <form action="index.html" method="post">
        <h1>Sign Up</h1>
        <input type="text" placeholder="用户名" class="txtb"/>
        <input type="email" placeholder="邮箱" class="txtb"/>
        <input type="password" placeholder="密码" class="txtb"/>
        <input type="submit" value="创建用户" class="signup-btn"/>
        <a href="#">登录</a>
    </form>
</div>
</body>
</html>