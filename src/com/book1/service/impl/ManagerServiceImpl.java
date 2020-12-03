package com.book1.service.impl;

import com.book1.dao.ManagerDao;
import com.book1.dao.impl.ManagerDaoImpl;
import com.book1.pojo.Manager;
import com.book1.pojo.User;
import com.book1.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
    private ManagerDao managerDao=new ManagerDaoImpl();
    @Override
    public boolean existsUsername(String username) {
        if(managerDao.queryByUsername(username)==null){
            return false;
        }
        return true;
    }

    @Override
    public Manager login(Manager manager) {
        return managerDao.queryByUsernameAndPassword(manager.getUsername(),manager.getPassword());

    }

    @Override
    public int updateManager(Manager manager) {
        return managerDao.updateManager(manager);
    }
}
