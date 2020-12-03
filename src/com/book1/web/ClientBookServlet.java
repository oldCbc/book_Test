package com.book1.web;

import com.book1.pojo.Book;
import com.book1.pojo.Page;
import com.book1.service.BookService;
import com.book1.service.impl.BookServiceImpl;
import com.book1.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
    BookService bookService=new BookServiceImpl();
    /**
     * 处理分页业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数 pageNo和pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用bookService.page(pageNo,pageSize),返回page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        page.setUrl("client/clientBookServlet?action=page");
        //3、保存page对象到req域中
        req.setAttribute("page",page);
        //4、请求转发到book_manager.jsp
        req.getRequestDispatcher("/client/index.jsp").forward(req,resp);

    }
    /**
     * 处理分页业务根据价格
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数 pageNo和pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min=WebUtils.parseInt(req.getParameter("min"),0);
        int max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);//默认值是一个定值，系统内定的
        //2、调用bookService.page(pageNo,pageSize),返回page对象
        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);
       // page.setUrl("client/clientBookServlet?action=pageByPrice");
        //防止按价格查询完后点击下一页等跳回默认查询的界面，有min则获取，没有则默认最小值；max一样
        page.setUrl("client/clientBookServlet?action=pageByPrice&min="+min+"&max="+max);
        //3、保存page对象到req域中
        req.setAttribute("page",page);
        //4、请求转发到book_manager.jsp
        req.getRequestDispatcher("/client/index.jsp").forward(req,resp);

    }

}
