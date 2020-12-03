package com.book1.test;

import com.book1.pojo.Book;
import com.book1.service.BookService;
import com.book1.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"aqeqwda","as111dada",null,488,999,new BigDecimal(999)));

    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(50);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(49,"ada","asdada",null,488,999,new BigDecimal(999)));
    }

    @Test
    public void queryBookById() {

        System.out.println(bookService.queryBookById(63));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookService.queryBooks());
    }
    @Test
    public void page(){
        System.out.println(bookService.page(1,4));
    }

}