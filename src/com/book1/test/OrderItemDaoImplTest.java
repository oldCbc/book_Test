package com.book1.test;

import com.book1.dao.OrderItemDao;
import com.book1.dao.impl.OrderItemDaoImpl;
import com.book1.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"Java从入门到自闭",1,new BigDecimal(999),new BigDecimal(999),"12312"));
        orderItemDao.saveOrderItem(new OrderItem(null,"mysql从删库到跑路",1,new BigDecimal(99999),new BigDecimal(99999),"12312"));
        orderItemDao.saveOrderItem(new OrderItem(null,"JavaScript从初识到放弃",1,new BigDecimal(999),new BigDecimal(999),"12312"));
    }
}