package com.book1.test;

import com.book1.pojo.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class fanshe {
    public static void main(String[] args) throws Exception {
        //1、将User类成为Class 的一个实例
        Class clazz= User.class;
        //2、①构造无参对象
        Constructor user = clazz.getConstructor();
        //实例化构造器，成为真正的对象
        User obj1 =(User)user.newInstance();


    /*    //②有参构造
        Constructor user1=clazz.getConstructor(int.class,String.class,String.class,String.class);
        //赋值
        User obj =(User )user1.newInstance(18, "dsad", "dasd", "adad");

*/
        System.out.println(obj1);


        //3、获取成员变量
        Field username = clazz.getDeclaredField("username");
        //允许访问私有属性
        username.setAccessible(true);
        //理解为obj1的username赋值set为asdadad
        username.set(obj1 ,"asdadad");

        System.out.println(obj1);
        System.out.println(obj1.getUsername());

        //4、调用方法
        Method method = clazz.getMethod("show", String.class);
        method.invoke(obj1 ,"adasda");

        //
        Class aClass = Class.forName("com.book1.pojo.User");
        System.out.println(aClass);
    }
}
