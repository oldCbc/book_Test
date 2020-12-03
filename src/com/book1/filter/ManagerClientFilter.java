package com.book1.filter;

import com.book1.pojo.Manager;
import com.book1.pojo.User;
import com.book1.service.ManagerService;
import com.book1.service.impl.ManagerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerClientFilter implements Filter {
    private ManagerService managerService=new ManagerServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //先强制转换为HttpServletRequest
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        //获取session中user的信息
        User user =(User) httpServletRequest.getSession().getAttribute("user");
        //判断username是否存在域管理员的数据库中
        String username = user.getUsername();
        boolean b = managerService.existsUsername(username);
        if(b){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            System.out.println("没有权限访问！");
            httpServletRequest.setAttribute("alert","对不起，您没有用户权限");
            httpServletRequest.getRequestDispatcher("/login_success.jsp").forward(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
