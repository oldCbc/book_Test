package com.book1.test;

import com.book1.dao.impl.BaseDao;
import com.book1.pojo.User;

public class sql_charu extends BaseDao {
    public int saveUser(User user) {
        for (int i=0;i<20;i++){

        }
        String sql="insert into t_user(`username`,`password`,`email`)values(?,?,?)";
         update(sql,user.getUsername(),user.getPassword(),user.getEmail());
        return 0;
    }
}
