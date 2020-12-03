package com.book1.web;

import com.book1.pojo.User;
import com.book1.service.UserService;
import com.book1.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Query extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数
        String username = req.getParameter("username");
        //调取Service的查询方法

        User user1 = new User(null, username, null, null);
        User query = userService.query(user1);

        //构造集合用来存储数据
        List<User> user=new ArrayList<User>();
        //向集合中添加查询数据
        user.add(query);

        //将结果存储到req域中
        req.setAttribute("key",user);
/*
        Object key1 = req.getAttribute("key");*/
        if(query==null){
           req.getRequestDispatcher("/Query_fail.jsp").forward(req,resp);
        }
        else
        //请求转发到jsp中遍历输出查询结果
        req.getRequestDispatcher("/Query1.jsp").forward(req,resp);

    }
}


