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
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();

    /**
     * 增加图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取book_edit的name属性为pageNo的value值,在book_manager中已经将pageNo值定义了pageTotal
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        //获取pageNo后永远在最后一页（超过不超过都是最后一页）
        pageNo+=1;

        //请求参数，封装javaBean对象，静态方法不能new对象，而是用类名调用
        //getParameterMap方法是获取表单的属性
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        /*System.out.println(book);*/
        //调用Service的add方法
        bookService.addBook(book);
        //跳转到图书页面
        //请求转发的时候如果是表单，F5刷新会重复提交，所以用重定向，（我没用表单，应该问题不大）
       // req.getRequestDispatcher("/bookServlet?action=list").forward(req,resp);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/bookServlet?action=page&pageNo="+pageNo);


    }

    /**
     * 删除图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数id，删除图书
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用Service中的delete方法
        bookService.deleteBookById(id);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    /**
     * 修改时完成对信息的修改
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 执行更新按钮后，获取更新后的所有信息，【底层根据id进行修改】
         */
        //获取请求参数
        Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book() );
        //调用BookService的update方法
        bookService.updateBook(book);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    /**
     * 查询所有图书信息显示在页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过bookService查询全部图书
        //将查询数据保存到request域中
        //请求转发到/book_manager.jsp
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/book_manager.jsp").forward(req,resp);


    }

    /**
     * 修改信息是回显所有信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 修改时重现修改的书籍全部信息
         */
        //获取请求参数
        //调用bookService。queryBookById查询图书
        //将查询信息set到域中
        //请求转发到book_edit。jsp显示信息来修改
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/book_edit.jsp").forward(req,resp);


    }

    /**
     * 处理分页业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数 pageNo和pageSize
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用bookService.page(pageNo,pageSize),返回page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        //设置url,就是在Page中加入一个url属性，让首页，尾页，这一页，那一页的共同代码都抽出来
        page.setUrl("bookServlet?action=page");
        //3、保存page对象到req域中
        req.setAttribute("page",page);
        //4、请求转发到book_manager.jsp
        req.getRequestDispatcher("/book_manager.jsp").forward(req,resp);
    }

}
