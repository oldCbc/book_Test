package com.book1.test;

import com.book1.dao.UserDao;
import com.book1.dao.impl.UserDaoImpl;
import com.book1.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDaoImpl userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        User admin = userDao.queryUserByUsername("admin");
        if(admin==null){
            System.out.println("用户不存在，可以建立新用户");
        }
        else
        System.out.println("用户名已存在，信息为："+admin);
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"asd","1212","aa@qq.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
        if(user==null){
            System.out.println("用户名或密码错误");
        }else
            System.out.println("查询成功"+user);
    }
}