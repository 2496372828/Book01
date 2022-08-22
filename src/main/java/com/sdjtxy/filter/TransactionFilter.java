package com.sdjtxy.filter;

import com.sdjtxy.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransactionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response1 = (HttpServletResponse) response;

        try {
            chain.doFilter(request, response);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            /*response1.sendRedirect("pages/cart/cart.jsp");*/
            throw new RuntimeException(e);//抛出异常到Tomcat服务器然后根据web.xml中配置的error

        }


    }
}
