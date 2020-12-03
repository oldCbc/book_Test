package com.book1.dao.impl;

import com.book1.dao.ManagerDao;
import com.book1.pojo.Manager;

public class ManagerDaoImpl extends BaseDao implements ManagerDao {
    @Override
    public Manager queryByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username` ,`password`, `email` from t_manager where `username`=? and `password`=?";
        return queryByUsernameAndPassword(username,password);
    }

    @Override
    public int saveManager(Manager manager) {
        String sql="insert into t_manager(`username`,`password`,`email`)values(?,?,?)";
        return update(sql,manager.getUsername(),manager.getPassword(),manager.getEmail());
    }

    @Override
    public int deleteByUsername(String username) {
        String sql="delete from t_manager where id =?";
        return update(sql,username);
    }

    @Override
    public int updateManager(Manager manager) {
        String sql="update t_manager set username=?,password=?,email=? where username=?";
        return update(sql,manager.getUsername(),manager.getPassword(),manager.getEmail(),manager.getUsername());
    }

    @Override
    public Manager queryByUsername(String username) {
        String sql="select `id`,`username` ,`password`, `email` from t_manager where `username`=?";
        return queryForOne(Manager.class,sql,username);
    }
}
