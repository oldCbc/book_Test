package com.book1.dao.impl;


import com.book1.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements com.book1.dao.OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        System.out.println("OrderItemDaoImpl 该线程名为"+Thread.currentThread().getName());
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)values(?,?,?,?,?)";
        return  update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
