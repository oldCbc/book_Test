package com.book1.test;

import com.book1.pojo.Cart;
import com.book1.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart=new Cart();
        CartItem cartItem = new CartItem();
        cart.addItem(new CartItem(1,1,"对对对",new BigDecimal(1999),new BigDecimal(1999)));
        System.out.println(cart);
        cart.addItem(new CartItem(2,1,"啛啛喳喳",new BigDecimal(1999),new BigDecimal(1999)));
        System.out.println(cart);
        cart.addItem(new CartItem(3,1,"呃呃鹅鹅鹅",new BigDecimal(199),new BigDecimal(199)));
        System.out.println(cart);
        System.out.println(cart.getItems().get(1));
    }

    @Test
    public void deleteItem() {
        Cart cart=new Cart();
        CartItem cartItem = new CartItem();
        cart.addItem(new CartItem(1,1,"对对对",new BigDecimal(1999),new BigDecimal(1999)));
        System.out.println(cart);
        cart.addItem(new CartItem(2,1,"啛啛喳喳",new BigDecimal(1999),new BigDecimal(1999)));
        System.out.println(cart);
        cart.addItem(new CartItem(3,1,"呃呃鹅鹅鹅",new BigDecimal(199),new BigDecimal(199)));
        System.out.println(cart);

        cart.deleteItem(1);
        System.out.println(cart.getItems().get(1));

    }

    @Test
    public void clear() {
        Cart cart=new Cart();
        CartItem cartItem = new CartItem();
        cart.addItem(new CartItem(1,1,"对对对",new BigDecimal(1999),new BigDecimal(1999)));
        cart.addItem(new CartItem(2,1,"啛啛喳喳",new BigDecimal(1999),new BigDecimal(1999)));
        cart.addItem(new CartItem(3,1,"呃呃鹅鹅鹅",new BigDecimal(199),new BigDecimal(199)));
        System.out.println(cart);
        cart.clear();
        System.out.println(cart);

    }

    @Test
    public void updateCount() {
        Cart cart=new Cart();
        CartItem cartItem = new CartItem();
        cart.addItem(new CartItem(1,1,"对对对",new BigDecimal(1999),new BigDecimal(1999)));
        cart.addItem(new CartItem(2,1,"啛啛喳喳",new BigDecimal(1999),new BigDecimal(1999)));
        cart.addItem(new CartItem(3,1,"呃呃鹅鹅鹅",new BigDecimal(199),new BigDecimal(199)));
        cart.updateCount(1,2);
        System.out.println(cart);
    }
}