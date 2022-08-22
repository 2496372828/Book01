package com.sdjtxy.web;

import com.sdjtxy.pojo.Book;
import com.sdjtxy.pojo.Page;
import com.sdjtxy.service.BookService;
import com.sdjtxy.service.impl.BookServiceImpl;
import com.sdjtxy.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

    private BookService bookService=new BookServiceImpl();

    protected void page(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        //获取pageNo和pageSize
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);//默认是第一页
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //调BookService层
        Page<Book> page=bookService.page(pageNo,pageSize);

        //page的url属性--决定分页条跳转后台manager or客户端client
        page.setUrl("client/bookServlet?action=page");
        //保存Page对象到Request域中
        request.setAttribute("page",page);
        //请求转发到book_manager.jsp页面中
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
    protected void selectPageByPrice(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        //获取pageNo和pageSize
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);//默认是第一页
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

         int minPrice=WebUtils.parseInt(request.getParameter("min"),0);
         int maxPrice=WebUtils.parseInt(request.getParameter("max"),Integer.MAX_VALUE);
         Page<Book> page=bookService.selectPageByPrice(pageNo,pageSize,minPrice,maxPrice);

         /*注意此处的对于价格的追加很重要*/
        StringBuilder sb = new StringBuilder("client/bookServlet?action=selectPageByPrice");
        // 如果有最小价格的参数,追加到分页条的地址参数中
        if (request.getParameter("min") != null) {
            sb.append("&min=").append(request.getParameter("min"));
        }
        // 如果有最大价格的参数,追加到分页条的地址参数中
        if (request.getParameter("max") != null) {
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());

        //将按价格查询到的page数据保存到request域中
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

}
