package com.book1.service.impl;

import com.book1.dao.UserDao;
import com.book1.dao.impl.UserDaoImpl;
import com.book1.pojo.User;
import com.book1.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){
            //等于null等于没查到，表示可注册
            return false;
        }
        return true;
    }

    @Override
    public User query(User user) {
        return userDao.queryUserByUsername(user.getUsername());
    }
}
