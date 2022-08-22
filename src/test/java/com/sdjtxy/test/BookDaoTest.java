package com.sdjtxy.test;

import com.sdjtxy.dao.BookDao;
import com.sdjtxy.dao.impl.BookDaoImpl;
import com.sdjtxy.pojo.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    @Test
    void addBook() {
        BookDao bookDao=new BookDaoImpl();
        Book book=new Book(null,"意林","意林出版社",new BigDecimal(12.00),300,500,null);
        bookDao.addBook(book);
    }

    @Test
    void deleteBookById() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void queryBookById() {
    }

    @Test
    void queryBooks() {
    }
    @Test
    public void queryForPageTotalCount(){
        BookDao bookDao=new BookDaoImpl();

        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void queryForItems(){
        BookDao bookDao=new BookDaoImpl();
        List<Book> bookList=bookDao.queryForItems(3,5);
        for(Book book:bookList){
            System.out.println(book);
        }
    }
}