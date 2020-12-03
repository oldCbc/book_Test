package com.book1.dao.impl;

import com.book1.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 注意加入ThreadLocal线程之后两个问题
 * ①不能关闭连接池，因为后边还得用这个对象，以为我们改成了自动提交，还没提交就关闭那么内容就提交不上去了
 * ②不能捕获异常不抛出，以为有异常我们得回滚，得在调用回滚的地方捕获异常，否则外边回滚的地方没有捕获异常也提交了不符合逻辑
 *
 */
public abstract class BaseDao {
    //使用DbUtils操作数据库
    //构造QueryRunner类的对象
        private QueryRunner queryRunner=new QueryRunner();


    /**
     *update()  数据库的update/delete/insert
     *如果返回-1则执行失败，否则返回影响的行数
     *增删改都是update
     * @param sql
     * @param args
     * @return
     */
    public  int update(String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }


    /**
     *     返回一个javaBean的sql语句
     *     type 返回的对象类型
     *     sql  执行的sql语句
     *     args sql对应的参数值
     *     <T>  返回的类型的泛型
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public  <T> T  queryForOne(Class<T>type,String sql,Object...args){
        Connection connection=JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        }  catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     *  返回多个javaBean的sql语句
     *    type 返回的对象类型
     *    sql  执行的sql语句
     *    args sql对应的参数值
     *    <T>List<T>  返回的集合类型的泛型
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(Class<T>type, String sql, Object...args){
        Connection connection=JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        }  catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    /**
     *执行返回一行一列的sql语句
     *args   sql对应的参数值
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql, Object...args){
        Connection connection=JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql, new ScalarHandler(),args);
        }  catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
