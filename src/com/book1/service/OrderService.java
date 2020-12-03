package com.book1.service;

import com.book1.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
