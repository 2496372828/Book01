package com.sdjtxy.web;

import com.sdjtxy.pojo.Book;
import com.sdjtxy.pojo.Page;
import com.sdjtxy.service.BookService;
import com.sdjtxy.service.impl.BookServiceImpl;
import com.sdjtxy.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 注意addBook方法中的"pageNo=pageNo+1"灵魂用法
 */
public class BookServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();



    /**
     * 查询图书集
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void queryList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Book> list=bookService.queryBooks();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 添加图书
     * @param request
     * @param response
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException {

        Book book=WebUtils.copyParameterToBean(new Book(),request.getParameterMap());
        bookService.addBook(book);
        /*
            此处我们要获得传来的当前页，因为添加图书默认添加在最末页，所以我们这里传参pageNo为最末页，但是要考虑一点：
            当我们添加图书时，最后一页已满，添加图书则会添加出新的一页，pageTotal则会加1，所以此时最末页已经并不是最末页了
            此时我们给得到的pageNo加1，此时就会跳到最后一页，即使添加图书前当页未满，此时pageNo是当前页，我们+1后，通过
            Web层的page方法中调用service层获取page对象，service层中page的setPageNo的数据边界的有效检查，使得我们添加的
            pageNo依然是pageTotal最后页。
         */
        int pageNo=WebUtils.parseInt(request.getParameter("pageNo"),1);
        pageNo=pageNo+1;

        //添加完图书之后--重定向回图书查询页面，防止用户浏览器F5刷新重复提交表单
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    /**
     * 删除图书
     * @param request
     * @param response
     * @throws IOException
     */
    protected void deleteBookById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取删除图书的ID
        bookService.deleteBookById(WebUtils.parseInt(request.getParameter("id"),0));
        //删除后返回到图书列表中
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page");


    }
    protected void getBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //获得getBook的Book的ID属性

        Book book=bookService.queryBookById(WebUtils.parseInt(request.getParameter("id"),0));
        //将图书信息保存到request域中
        request.setAttribute("book",book);

        //请求转发到book_edit.jsp页面中
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    protected void updateBook(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {

        //获取表单提交的修改的Book对象
        //将操作对象id放入新Book对象
        Book book=new Book();
        Integer it=WebUtils.parseInt(request.getParameter("id"),0);
        book.setId(it);
        WebUtils.copyParameterToBean(book,request.getParameterMap());
        bookService.updateBook(book);
        int pageNo=WebUtils.parseInt(request.getParameter("pageNo"),1);

        //重定向到book_manager.jsp展示全部图书列表
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);

    }
    protected void page(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //获取pageNo和pageSize
        int pageNo=WebUtils.parseInt(request.getParameter("pageNo"),1);//默认是第一页
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //调BookService层
        Page<Book> page=bookService.page(pageNo,pageSize);

        //page的url属性--决定分页条跳转后台manager or客户端client
        page.setUrl("manager/bookServlet?action=page");

        //保存Page对象到Request域中
        request.setAttribute("page",page);
        //请求转发到book_manager.jsp页面中
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }


}
