package com.book1.dao.impl;

import com.book1.dao.OrderDao;
import com.book1.pojo.Order;


public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        System.out.println("OrderDaoImpl 该线程名为"+Thread.currentThread().getName());

        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
