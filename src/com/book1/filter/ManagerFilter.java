package com.book1.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强制转换得到的HttpServletRequest对象才有getSession方法
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        //获取session中名为user的判断登录权限
        Object user = httpServletRequest.getSession().getAttribute("user");
        if(user==null){
            //如果没登录，返回登录页面
            httpServletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
        }else {

            //如果登录，继续执行（有filter就执行，没有就访问资源）
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
