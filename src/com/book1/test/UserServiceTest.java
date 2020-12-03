package com.book1.test;

import com.book1.pojo.User;
import com.book1.service.UserService;
import com.book1.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;
public class UserServiceTest {

    @After
    public void tearDown() throws Exception {
    }
    UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"asda","asda","asda@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"asda","asda",null)));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("asda")){
            System.out.println("用户名已存在！");
        }else
            System.out.println("用户名不存在！可用!");

    }
}