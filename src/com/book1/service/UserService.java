package com.book1.service;

import com.book1.pojo.User;

public interface UserService {
    /*
    注册用户的业务
     */
    public void registUser(User user);
    /*
    登录的业务
     */
    public User login(User user);
    /*
    检查用户名是否可用
    返回true表示已存在
     */
    public boolean existsUsername(String username);
    /*
    查询信息
     */
    public User query(User user);
}
