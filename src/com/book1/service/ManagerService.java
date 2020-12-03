package com.book1.service;

import com.book1.pojo.Manager;
import com.book1.pojo.User;

public interface ManagerService {
    public boolean existsUsername(String username);
    public Manager login(Manager manager);
    public int updateManager(Manager manager);
}
