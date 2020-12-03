package com.book1.test;

import com.book1.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {
    @Test
    public void JdbcUtilsTest(){
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
        JdbcUtils.commitAndClose();
    }
}
