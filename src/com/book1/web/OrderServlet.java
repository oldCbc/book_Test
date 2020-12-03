package com.book1.web;

import com.book1.pojo.Cart;
import com.book1.pojo.User;
import com.book1.service.OrderService;
import com.book1.service.impl.OrderServiceImpl;
import com.book1.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{
    private OrderService orderService=new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("结账失败");
        //先获取Cart购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        //获取UserId
        User user =(User) req.getSession().getAttribute("user");
        //若用户没登录则蹦到登录页面
        if(user==null){
            req.getRequestDispatcher(req.getContextPath()+"/login.jsp").forward(req,resp);
            //若请求转发或重定向发生，立即中断以下代码
            return;
        }

        System.out.println("OrderServlet该线程名为"+Thread.currentThread().getName());

        Integer id = user.getId();
        //调用OrderService.createOrder（Cart,UserId）方法
        String orderId = orderService.createOrder(cart, id);

        /* req.setAttribute("orderId",orderId)*/
        req.getSession().setAttribute("orderId",orderId);
        //请求转发页面
        System.out.println("结账成功");
        //request域数据不支持重定向，所以必须用session域数据
/*        req.getRequestDispatcher("/order/order.jsp").forward(req,resp);*/
        resp.sendRedirect(req.getContextPath()+"/order/order.jsp");
    }
}
