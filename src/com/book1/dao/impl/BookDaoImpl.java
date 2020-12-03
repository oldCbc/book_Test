package com.book1.dao.impl;

import com.book1.dao.BookDao;
import com.book1.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao{

    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(id,name,price,author,sales,store,img_path)values(?,?,?,?,?,?,?)";
        return update(sql,book.getId(),book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStore(),book.getImg_path());
    }

    @Override
    public int deleteById(Integer id) {
        String sql="delete from t_book where id =?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?,price=?,author=?,sales=?,store=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStore(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select id,name,price,author,sales,store,img_path from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select id ,name,price,author,sales,store,img_path from t_book";
        return queryForList(Book.class,sql);
    }

    /**
     * 分页开始
     * @return
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from t_book";
        Number number=(Number)queryForSingleValue(sql);
        return number.intValue();

    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select id ,name,price,author,sales,store,img_path from t_book limit ?,?";

        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min,int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        Number number=(Number)queryForSingleValue(sql,min,max);
        return number.intValue();
    }

    /**
     * 查询最小和最大价格
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select id ,name,price,author,sales,store,img_path from t_book where price between ? and ? order by price limit ?,?";

        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
