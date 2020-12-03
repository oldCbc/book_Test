package com.book1.test;

import com.book1.pojo.Cart;
import com.book1.pojo.CartItem;
import com.book1.service.OrderService;
import com.book1.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart=new Cart();
        CartItem cartItem = new CartItem();
        cart.addItem(new CartItem(1,1,"Java从入门到自闭",new BigDecimal(1999),new BigDecimal(1999)));
        cart.addItem(new CartItem(2,1,"mysql从删库到跑路",new BigDecimal(1999),new BigDecimal(1999)));
        cart.addItem(new CartItem(3,1,"JavaScript从初识到放弃",new BigDecimal(199),new BigDecimal(199)));
        OrderServiceImpl orderService = new OrderServiceImpl();
        //传入的userId必须是参照user_id
        System.out.println("您的订单编号是"+orderService.createOrder(cart,1));

    }
}