package com.book1.dao;

import com.book1.pojo.Book;
import com.book1.pojo.Manager;

public interface ManagerDao {
    public Manager queryByUsernameAndPassword(String username,String password);
    public int saveManager(Manager manager);
    public int deleteByUsername(String username);
    public int updateManager(Manager manager);
    public Manager queryByUsername(String username);
}
