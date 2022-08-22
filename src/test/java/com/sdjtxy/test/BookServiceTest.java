package com.sdjtxy.test;

import com.sdjtxy.pojo.Book;
import com.sdjtxy.pojo.Page;
import com.sdjtxy.service.BookService;
import com.sdjtxy.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    void page(){
        BookService bookService=new BookServiceImpl();
        Page<Book> page=bookService.page(1,5);
        System.out.println(page);
    }
}