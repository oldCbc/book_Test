package com.book1.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    /**
     * 读取配置文件信息
     */
    private static DruidDataSource dataSource;
    //同一个数据库连接对象放在同一个线程中
    private static ThreadLocal<Connection> conns=new ThreadLocal<Connection>();
    static {

        try {
            Properties properties=new Properties();
            //读取jdbc.properties的配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //流中加载数据
            properties.load(inputStream);
            //创建连接池
            dataSource=(DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     *  获取数据库连接池，成功返回连接，失败则null
     * @return conn
     *
     */
    public static Connection getConnection() {
        Connection conn=conns.get();
        if(conn==null)
        {
            try {
                //若没有连接池对象，则获取然后保存，供后边jdbc操作
                conn=dataSource.getConnection();
                conns.set(conn);
                //设置为手动提交事务
                conn.setAutoCommit(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭资源
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection!=null){
            try {
                //提交并关闭事务
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        }
        //一定要执行remove方法，因为tomcat底层使用了线程池的技术
        conns.remove();

    }

    /**
     * 回滚事务并关闭资源
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection!=null){
            try {
                //回滚并关闭事务
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove方法，因为tomcat底层使用了线程池的技术
        conns.remove();

    }

/*
    *//**
     * 关闭数据库池
     * @param conn
     *//*
    public static void close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/


}
