package com.sdjtxy.service.impl;

import com.sdjtxy.dao.BookDao;
import com.sdjtxy.dao.impl.BookDaoImpl;
import com.sdjtxy.pojo.Book;
import com.sdjtxy.pojo.Page;
import com.sdjtxy.service.BookService;

import java.util.List;

/**
 * 注意问题：数据边界的有效检查
 */
public class BookServiceImpl  implements BookService {
    BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return  bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return  bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);
        //获取总记录数
        int pageTotalCount=bookDao.queryForPageTotalCount();

     //求总页码
        Integer pageTotal;
        if((pageTotalCount%pageSize>0)==true){
             pageTotal=pageTotalCount/pageSize+1;
        }else{
             pageTotal=pageTotalCount/pageSize;
        }
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        /*
            此处不可给index赋值为(pageNo-1)*pageSize
            因为会有数据边界越界的情况，比如输入500，这时虽然在setPageNo()中有对于pageNo的判断，但在这儿并没有
            所以我们要对赋值index时赋值应该是在setPageNo中进行筛选判断过后的pageNo值，再通过歌坛getPageNo()方法赋值。

        */
        int begin=(page.getPageNo()-1)*pageSize;

        //得到分页中的item Book数据
        List<Book> items=bookDao.queryForItems(begin,pageSize);
        //分页中items赋值
        page.setItems(items);


        //总记录数赋值
        page.setPageTotalCount(pageTotalCount);

        return page;

    }

    @Override
    public Page<Book> selectPageByPrice(int pageNo,int pageSize,int minPrice, int maxPrice) {
        Page<Book> page=new Page<>();
        /*
            求：
                当前页码数 固定从1开始

                每页显示数量

                总记录数

                页码总数:
                pageTotal=totalCount/pageSize;
                pageTotal=totalCount/pageSize>0 ?pageTotal+1:pageTotal;

                当前页数据item
         */
        //得到查询全部数量
        int pageTotalCount=bookDao.queryForPageTotalCountByPrice(minPrice,maxPrice);
        page.setPageTotalCount(pageTotalCount);

        //固定pageSize为4
        page.setPageSize(pageSize);

        //得到页码总数
        int pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount/pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //固定查询当前页从首页开始
        page.setPageNo(pageNo);





        //得到分页中item
        List<Book> list=bookDao.queryForItemsByPrice(minPrice, maxPrice,page.getPageNo(),pageSize);
        page.setItems(list);

        return page;
    }


}
