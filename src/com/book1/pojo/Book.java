package com.book1.pojo;

import java.math.BigDecimal;

public class Book {
    //定义Integer因为赋值的时候可以传null而不是必须传值，因为id是自增，经常传null，int不能为null
    private Integer id;
    private String name;
    private String author;
    //下边判断img_path是否为null。是null则不赋值，name就是用这个赋值的默认值
    private String img_path="http://localhost:8080/book_Test/static/img/book.jpg";
    private Integer sales;
    private Integer store;
    //BigDecimal用的时候得new对象传入数值
    private BigDecimal price;
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", img_path='" + img_path + '\'' +
                ", sales=" + sales +
                ", store=" + store +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        //要求给定的图书封面图书路径不能为空
        if(img_path!=null&&!"".equals(img_path)) {
            this.img_path = img_path;
        }
        /*this.img_path = img_path;*/
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Book(Integer id, String name, String author, String img_path, Integer sales, Integer store, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.author = author;
        //要求给定的图书封面图书路径不能为空
        if(img_path!=null&&!"".equals(img_path)) {
            this.img_path = img_path;
        }
        this.sales = sales;
        this.store = store;
        this.price = price;
    }

    public Book() {
    }
}
