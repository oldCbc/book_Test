package com.book1.test;

import com.book1.dao.OrderDao;
import com.book1.dao.impl.OrderDaoImpl;
import com.book1.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao=new OrderDaoImpl();
        orderDao.saveOrder(new Order("12312",new Date(),new BigDecimal(100),0,1));
    }
}