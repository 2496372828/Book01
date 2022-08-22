package com.sdjtxy.service;

import com.sdjtxy.pojo.Book;
import com.sdjtxy.pojo.Page;

import java.util.List;

/**
 * BookService层可与DAO层连接操作数据库，也可与Web层连接
 *
 */
public interface BookService {
    /**
     *  添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     *  通过图书id删除图书
     * @param id
     * @return  返回删除图书记录条数
     */
    public int deleteBookById(Integer id);

    /**
     *
     * @param book
     * @return  返回更新图书记录条数
     */
    public int updateBook(Book book);

    /**
     *
     * @param id
     * @return 返回查询图书记录
     */
    public Book queryBookById(Integer id);

    /**
     *
     * @return List<Book> 返回所有图书集
     */
    public List<Book> queryBooks();

    /**
     *
     * @param pageNo
     * @param pageSize
     * @return 返回分页page对象
     */
    Page<Book> page(int pageNo, int pageSize);

    Page<Book> selectPageByPrice(int pageNo,int pageSize,int minPrice, int maxPrice);
}
