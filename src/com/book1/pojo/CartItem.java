package com.book1.pojo;

import java.math.BigDecimal;

public class CartItem {
    /**
     * 购物车的商品项
     */
    private Integer id;
    private Integer count;
    private String name;
    private BigDecimal price;
    //这是每种图书的总价格
    private BigDecimal totalPrice;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal gettotalPrice() {
        return totalPrice;
    }

    public void settotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartItem(Integer id, Integer count, String name, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.count = count;
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public CartItem() {
    }
}
