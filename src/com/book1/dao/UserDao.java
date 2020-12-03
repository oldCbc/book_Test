package com.book1.dao;

import com.book1.pojo.User;

public interface UserDao {

    /*
    根据用户名查询用户信息
     */
   public User queryUserByUsername(String username);

    /*
    保存用户信息
     */
    public int saveUser(User user);
    /*
    登录上，根据用户名和密码查询
    如果null，则密码或用户名错误
     */
    public User queryUserByUsernameAndPassword(String username,String password);
}
