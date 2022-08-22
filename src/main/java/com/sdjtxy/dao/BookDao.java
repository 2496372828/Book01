package com.sdjtxy.dao;

import com.sdjtxy.pojo.Book;

import java.util.List;

public interface BookDao {

    /**
     *  添加图书
     * @param book
     * @return
     */
    public  void addBook(Book book);

    /**
     *通过图书ID来删除图书
     * @param id
     * @return 返回-1代表删除执行失败
     */
    public  int deleteBookById(Integer id);

    /**
     * 更新图书
     * @param book
     * @return 返回更新图书条数
     */
    public  int updateBook(Book book);

    /**
     * 查询图书
     * @param id
     * @return 返回查询Book
     */
    public  Book queryBookById(Integer id);

    /**
     * 查询全部图书
     * @return 返回集合(包含所有查询条数)
     */
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForItems(int begin, int pageSize);

    List<Book> queryForItemsByPrice(int minPrice,int maxPrice,int begin,int pageSize);

    Integer queryForPageTotalCountByPrice(int minPrice,int maxPrice);
}
