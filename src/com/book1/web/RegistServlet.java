package com.book1.web;

import com.book1.pojo.User;
import com.book1.service.UserService;
import com.book1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数，获取的是name 属性值！！！！！！！！！！！！！！！！！！！！！
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String Vc = req.getParameter("Vc");
        //2、验证码====》先必须abcde
        System.out.println("用户名"+username+"密码"+password+"邮箱"+email+"验证码"+Vc);
        if("abcde".equalsIgnoreCase(Vc)){
            if(userService.existsUsername(username)){
                System.out.println("用户名已存在");
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                //请求转发转至另一个servlet，  /  代表至http://localhost:8080/book_Test/web
                req.getRequestDispatcher("/regist_static.jsp").forward(req,resp);
            }else {
                userService.registUser(new User(null,username,password,email));
                req.getRequestDispatcher("/regist_success.jsp").forward(req,resp);
            }
        }else {
/*
            //控制台打印网页信息
            PrintWriter out= resp.getWriter();
            out.print("验证码："+Vc+"错误");
*/

            //错误则跳回登录页面
            System.out.println("验证码："+Vc+"错误");
            req.getRequestDispatcher("/regist_static.jsp").forward(req,resp);
        }

    }
}
