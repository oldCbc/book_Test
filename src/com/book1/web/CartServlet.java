package com.book1.web;

import com.book1.pojo.Book;
import com.book1.pojo.Cart;
import com.book1.pojo.CartItem;
import com.book1.service.BookService;
import com.book1.service.impl.BookServiceImpl;
import com.book1.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("加入购物车成功");
        //获取请求参数id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用BookServlet.queryBookById(id)来获取图书的信息
        Book book = bookService.queryBookById(id);
        //运用cart.addItem（cart）来获取的图书信息转为商品项加入购物车
        //因为这次的购物车没经过数据库，只存在session中，与session共存亡，所以讲cart对象放在session中，若存在，那么继续使用；若不存在，则创建
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        CartItem cartItem=new CartItem(id,1,book.getName(),book.getPrice(),book.getPrice());
        cart.addItem(cartItem);
        System.out.println(cart);
        //最后一个商品信息
        req.getSession().setAttribute("lastName",cartItem.getName());
        System.out.println(cartItem.getName());

        //重定向到商品列表页面(首页)
        //req.getHeader("Referer")是获取响应头的Referer信息，这个属性的值就是当时客户端发给服务器的真实地址
        // （不单单指向首页，还要保存所有地址信息如pageNo值 ）
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }

    /**
     * 通过AJAX的响应流响应数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //判断用户是否登录，用户信息是存在session域中，user的session【登录的时候就创建就将用户信息存储在了session域中】
//        Object user = req.getSession().getAttribute("user");
//        System.out.println(user);
//
//        if(user==null){
//            System.out.println("请登录后操作");
//           req.getRequestDispatcher("/login.jsp").forward(req,resp);
//            resp.sendRedirect("http://localhost:8080/book_Test/login.jsp");
//        }

        //如果已登录才能加购物车
//        else {
            System.out.println("加入购物车成功");
            //获取请求参数id
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            //调用BookServlet.queryBookById(id)来获取图书的信息
            Book book = bookService.queryBookById(id);
            //运用cart.addItem（cart）来获取的图书信息转为商品项加入购物车
            //因为这次的购物车没经过数据库，只存在session中，与session共存亡，所以讲cart对象放在session中，若存在，那么继续使用；若不存在，则创建
            Cart cart=(Cart) req.getSession().getAttribute("cart");
            if(cart==null){
                cart=new Cart();
                req.getSession().setAttribute("cart",cart);
            }
            CartItem cartItem=new CartItem(id,1,book.getName(),book.getPrice(),book.getPrice());
            cart.addItem(cartItem);
            System.out.println(cart);
            //最后一个商品信息
            req.getSession().setAttribute("lastName",cartItem.getName());
            System.out.println(cartItem.getName());

            //以响应流的形式发给客户端操作之后的页面变化【总的书籍数量和最后一个添加的数据名称】
            //①构造Map集合将传送的数据放在集合中
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("lastName",cartItem.getName());
            map.put("totalCount",cart.getTotalCount());
            //②将集合转为Gson对象
            Gson gson=new Gson();
            String resultgson = gson.toJson(map);
            //③利用响应流的形式传回gson值，页面利用getgson来输出值
            resp.getWriter().write(resultgson);
    }



    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //获取商品信息
        Cart cart =(Cart)req.getSession().getAttribute("cart");
        //调用pojo类中的deleteItem方法删除
        cart.deleteItem(id);
        //重定向请求页面
/*        resp.sendRedirect(req.getHeader("Regerer"));*/
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        System.out.println("清空完毕11111！");
        if(cart!=null){
            cart.clear();
            System.out.println("清空完毕！");
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        int count=WebUtils.parseInt(req.getParameter("count"),1);
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        cart.updateCount(id,count);
        resp.sendRedirect(req.getHeader("Referer"));


    }
}
