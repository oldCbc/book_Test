package com.book1.web;

import com.book1.pojo.User;
import com.book1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private UserServiceImpl userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  //      resp.setContentType("text/html;charset=UTF-8");
  //      PrintWriter writer = resp.getWriter();

        //1、获取请求参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        System.out.println("用户名"+username+"密码"+password);

        //2、调用UserServiceImpl处理业务
        User login = userService.login(new User(null, username, password, null));
        if(login==null){
            System.out.println("用户名和密码错误，登陆失败");
     //       writer.print("检测能否输出");
            //如果信息错误，则回显用户名
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }else {
            System.out.println("登陆成功！");
            req.getRequestDispatcher("/login_success.jsp").forward(req,resp);
        }


    }
}
