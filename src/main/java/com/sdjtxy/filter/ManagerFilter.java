package com.sdjtxy.filter;

import com.sdjtxy.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user==null){
             //用户未注册 无法访问管理页面
            //我们此处使用filter拦截未登录用户访问后台页面，给manager/下的jsp文件设置拦截过滤，
            //但是有一点注意，老手还可以使用manager/bookServlet?action=xxx&xx 通过访问浏览器地址上访问servlet程序也可以访问到后台页面
            //所以此处我们配置拦截路径时，还需将servlet程序也拦截
            //跳转到登录页面
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }

        chain.doFilter(request, response);
    }
}
