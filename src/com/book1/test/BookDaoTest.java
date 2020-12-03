package com.book1.test;

import com.book1.dao.BookDao;
import com.book1.dao.impl.BookDaoImpl;
import com.book1.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
//alt+shift+T
public class BookDaoTest {
    private BookDao bookDao=new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"ada","asdada",null,488,999,new BigDecimal(999)));;
    }

    @Test
    public void deleteById() {
        bookDao.deleteById(51);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(51,"a121da","asdada",null,488,999,new BigDecimal(999)));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(41));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());

    }
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,9999));

    }
    @Test
    public void  queryForPageItems() {

        System.out.println(bookDao.queryForPageItems(1,4));
    }
@Test
    public void  queryForPageItemsByPrice() {

        System.out.println(bookDao.queryForPageItemsByPrice(0,4,9999,9999999));
    }


}