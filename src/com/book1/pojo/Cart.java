package com.book1.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    /**
     * 购物车对象
     */
    //目前这两个全局变量没有意义，set方法不能使用（因为这两个变量是随其他变量改变而改变），所以仅在方法中定义局部变量即可，toString中调用方法即可
  /*  private Integer totalCount;
    private BigDecimal totalPrice;*/
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品数量
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //首先查看是否存在商品
        CartItem cart=items.get(cartItem.getId());
        if(cart==null){
            //想Map集合中加入新的值
            items.put(cartItem.getId(),cartItem);
        }else {
            //修改数量
            cart.setCount(cart.getCount()+1);
            //修改总价,必须用muliply，因为是对象之间的乘
            cart.settotalPrice(cart.getPrice().multiply(new BigDecimal(cart.getCount())));
        }

    }

    /**
     * 根据id删除商品
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        //判断是否存在，现货区对象
        CartItem cartItem=items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
/*            cartItem.setCount(cartItem.getCount()+1);*/
            cartItem.settotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        //遍历集合，累加所有数量
        for (Map.Entry<Integer, CartItem> integerCartItemEntry : items.entrySet()) {
            //getValue是获取键值对中的值，而这个Map的value值是一个CartItem对象，再用这个对象的get方法调取数量
            totalCount += integerCartItemEntry.getValue().getCount();
        }
        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        //这是购物车的总价格
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> integerCartItemEntry : items.entrySet()) {
            totalPrice =totalPrice.add(integerCartItemEntry.getValue().gettotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}

