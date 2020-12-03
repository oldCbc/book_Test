package com.book1.filter;

import com.book1.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 事务的终极管理，【本工程下的所有文件都加入了事务管理，要么同时提交关闭资源，要么回滚关闭资源】
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);

            JdbcUtils.commitAndClose();//提价事务

        } catch (IOException e) {

            JdbcUtils.rollbackAndClose();//回滚事务

            e.printStackTrace();
            //抛出异常给服务器，用来转向错误界面
            throw new RuntimeException();
        }

    }

    @Override
    public void destroy() {

    }
}
