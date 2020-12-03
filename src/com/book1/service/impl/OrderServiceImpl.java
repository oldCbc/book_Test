package com.book1.service.impl;

import com.book1.dao.BookDao;
import com.book1.dao.OrderDao;
import com.book1.dao.OrderItemDao;
import com.book1.dao.impl.BookDaoImpl;
import com.book1.dao.impl.OrderDaoImpl;
import com.book1.dao.impl.OrderItemDaoImpl;
import com.book1.pojo.*;
import com.book1.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private OrderDao orderDao=new OrderDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //创建订单===
        //订单号唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        //创建订单对象
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        // 保存订单
        orderDao.saveOrder(order);

       // int a=12/0; 模拟异常来回滚

        System.out.println("OrderServiceImpl 该线程名为"+Thread.currentThread().getName());
        //创建订单项====
        //遍历Cart中的所有信息输出给OrderItem
        for(Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){

            CartItem cartItem = entry.getValue();

            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.gettotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //每提交一次数据就得更新库存和销量
            Book book=bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStore(book.getStore()-cartItem.getCount());
            bookDao.updateBook(book);

        }
        //清空购物车
        cart.clear();
        return orderId;
    }
}
