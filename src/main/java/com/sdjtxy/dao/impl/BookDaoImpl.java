package com.sdjtxy.dao.impl;

import com.sdjtxy.dao.BookDao;
import com.sdjtxy.pojo.Book;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public void addBook(Book book) {
        String sql="insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        return  update(sql,id);

    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select *from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select *from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from t_book";

        /*
        此处对于Object转型成Integer，不可强转
        java.lang.ClassCastException:
        class java.lang.Long cannot be cast to class java.lang.Integer (java.lang.Long and java.lang.Integer are in module java.base of loader 'bootstrap')
         */
        Number count=(Number)queryForSingleValues(sql);
        return count.intValue();


    }


    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql="select *from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public List<Book> queryForItemsByPrice(int minPrice, int maxPrice,int begin,int pageSize) {
        String sql="select *from t_book where price between ? and ? order by price limit ?,?";
        List<Book> list=queryForList(Book.class,sql,minPrice,maxPrice,begin,pageSize);
        return list;
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int minPrice, int maxPrice) {

        String sql="select count(*) from t_book where price between ? and ?";
        Number count=(Number)queryForSingleValues(sql,minPrice,maxPrice);
        return count.intValue();
    }
}
