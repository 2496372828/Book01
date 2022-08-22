package com.sdjtxy.web;

import com.google.gson.Gson;
import com.sdjtxy.pojo.Book;
import com.sdjtxy.pojo.Cart;
import com.sdjtxy.pojo.CartItem;
import com.sdjtxy.pojo.User;
import com.sdjtxy.service.BookService;
import com.sdjtxy.service.impl.BookServiceImpl;
import com.sdjtxy.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
        BookService bookService=new BookServiceImpl();

        /**
         * 在购物车中添加商品
         * @param request
         * @param response
         * @throws IOException
         */
        protected void addItem(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
                //首先检查用户是否登录，未登录不能将商品添加到购物车中，跳转到登陆页面
                User user=(User)request.getSession().getAttribute("user");
                if(user==null){
                        //未登录，跳转登录
                        //response.sendRedirect("pages/user/login.jsp");
                        request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
                        return;
                }

                // 获取请求的参数 商品编号
                int id= WebUtils.parseInt(request.getParameter("id"),0);
                //得到Book对象
                Book book = bookService.queryBookById(id);
                CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
                Cart cart=(Cart)request.getSession().getAttribute("cart");
                if(cart==null){
                        //没有购物车对象就创建
                        cart=new Cart();
                        request.getSession().setAttribute("cart",cart);

                }
                cart.addItem(cartItem);
                //请求头referer的值:request.getHeader("Referer")可以获得当前访问浏览器上方地址栏的信息
                //System.out.println("请求头referer的值"+request.getHeader("Referer"));

                //设置购物车模块中信息
                //在Session域中已存放cart购物车对象，在cart.jsp中我们需要展示购物车种商品的信息，即总金额，总商品数量等等

                //将新加的图书添加到Session域中，便于首页购物车的回显
                request.getSession().setAttribute("newBookName",book.getName());
                response.sendRedirect(request.getHeader("Referer"));


        }

        protected void aJaxAddItem(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
                User user=(User)request.getSession().getAttribute("user");
                if(user==null){
                        //未登录，跳转登录
                        //response.sendRedirect("pages/user/login.jsp");
                        request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
                        return;
                }

                // 获取请求的参数 商品编号
                int id= WebUtils.parseInt(request.getParameter("id"),0);
                //得到Book对象
                Book book = bookService.queryBookById(id);
                CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
                Cart cart=(Cart)request.getSession().getAttribute("cart");
                if(cart==null){
                        //没有购物车对象就创建
                        cart=new Cart();
                        request.getSession().setAttribute("cart",cart);

                }
                cart.addItem(cartItem);
                //请求头referer的值:request.getHeader("Referer")可以获得当前访问浏览器上方地址栏的信息
                //System.out.println("请求头referer的值"+request.getHeader("Referer"));

                //设置购物车模块中信息
                //在Session域中已存放cart购物车对象，在cart.jsp中我们需要展示购物车种商品的信息，即总金额，总商品数量等等

                //将新加的图书添加到Session域中，便于首页购物车的回显
                request.getSession().setAttribute("newBookName",book.getName());
                Map<String,Object> resultMap = new HashMap<String,Object>();
                resultMap.put("totalCount", cart.getTotalCount());
                resultMap.put("lastName",cartItem.getName());
                Gson gson = new Gson();
                String resultMapJsonString = gson.toJson(resultMap);
                response.getWriter().write(resultMapJsonString);



        }







        protected void deleteItem(HttpServletRequest request,HttpServletResponse response) throws IOException {
                //通过图书id来删除购物车中的所选的图书
                int id=WebUtils.parseInt(request.getParameter("id"),0);
                Cart cart =(Cart)request.getSession().getAttribute("cart");
                //此处不用判断进行session域中cart购物车对象的是够存在
                cart.deleteItem(id);
                //删除后重定向回购物车页面
                response.sendRedirect(request.getHeader("Referer"));

        }
        protected void clear(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
                //清空购物车
                Cart cart=(Cart) request.getSession().getAttribute("cart");
                if(cart==null){
                        //没有就创建
                        cart=new Cart();
                        request.getSession().setAttribute("cart",cart);
                }
                cart.clear();
                //清空购物车后返回到购物车页面
                //request.getRequestDispatcher("").forward(request,response);
                response.sendRedirect(request.getHeader("Referer"));


        }
        protected void updateCount(HttpServletRequest request,HttpServletResponse response) throws IOException {
                //首先获取购物车中需要更改的商品信息

                int id=WebUtils.parseInt(request.getParameter("id"),0);
                int count=WebUtils.parseInt(request.getParameter("count"),1);
                Cart cart=(Cart)request.getSession().getAttribute("cart");
                if(cart!=null){

                        cart.updateCount(id,count);
                        System.out.println(cart);
                        //更改完购物车中商品信息后重定向到cart.jsp中
                        response.sendRedirect(request.getHeader("Referer"));
                }
        }

}
