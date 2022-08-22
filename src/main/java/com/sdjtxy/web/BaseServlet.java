package com.sdjtxy.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 关于BaseServlet的抽取，一般来说我们这个servlet程序不仅包括UserServlet用户模块，还会有图书模块等等，
 * 都会使用反射对获取的action来调用相应模块的业务方法，所以我们要抽取出BaseServlet，
 * 然后每个模块servlet程序来继承这个BaseServlet，在每个功能模块中编写相应的action业务方法就可以
 * 这里写的"method.invoke(this,request,response);"中的this就很好体现了这一点，this也就是当前对象，子类对象来调用继承的doPost方法来通过反射来执行业务方法
 *
 * 所谓代码优化过程，也就是一步步的解耦合过程
 *
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String action=request.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);//这里对象实例填this即可，当前对象来调用
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//异常抛给Filter过滤器
        }

    }
}
