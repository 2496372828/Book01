package com.sdjtxy.web;

import com.sdjtxy.dao.OrderDao;
import com.sdjtxy.pojo.Cart;
import com.sdjtxy.pojo.Order;
import com.sdjtxy.pojo.OrderItem;
import com.sdjtxy.pojo.User;
import com.sdjtxy.service.OrderService;
import com.sdjtxy.service.impl.OrderServiceImpl;
import com.sdjtxy.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    OrderService orderService=new OrderServiceImpl();
    protected void createOrder(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //生成订单---获取 1.用户的购物车  2.用户的userId
        //得到Session域中的cart购物车
        User user = (User) request.getSession().getAttribute("user");
        Cart cart=(Cart) request.getSession().getAttribute("cart");

        if (user == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }

        Integer userId=user.getId();

        //获取订单ID
        String orderId=orderService.createOrder(cart,userId);


        request.getSession().setAttribute("orderId",orderId);

        //生成订单后清空购物车
        cart.clear();

        //重定向到checkout.jsp页面
        response.sendRedirect("pages/cart/checkout.jsp");

    }
    protected void queryAllOrders(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //查看所有订单(管理员)--发送到order.jsp页面上
        //1.获取订单
        //2.将订单放到Session域中
        //3.在order_manager.jsp页面中显示订单

        List<Order> orders = orderService.queryAllOrders();
        request.getSession().setAttribute("orders",orders);

        response.sendRedirect("pages/manager/order_manager.jsp");
    }
    protected void queryMyOrders(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //查看我(登录用户)的订单
        //1.获取userId
        //2.将订单放到Session域中
        //3.在order.jsp页面中显示订单

        User user = (User)request.getSession().getAttribute("user");
        int userId=user.getId();

        //获取userId的订单
        List<Order> orders=orderService.queryOrderByUserId(userId);

        request.getSession().setAttribute("ordersByUserId",orders);
        response.sendRedirect("pages/order/order.jsp");


    }



    protected void sendOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //订单发货操作
        //获取订单ID
         String orderId=request.getParameter("orderId");

         //调service层
         orderService.sendOrder(orderId);

         //获取最新的订单状态情况 重定向到获取最新订单项servlet程序
        response.sendRedirect("orderServlet?action=queryAllOrders");


    }
    protected void showOrderDetails(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //查看订单中的订单各项
        //获取需求查看订单的订单ID
        String orderId=request.getParameter("orderId");

        //根据订单id来获取订单项,并放到Session域中
        List<OrderItem> orderItems=orderService.queryOrderItemsByOrderID(orderId);
        request.getSession().setAttribute("orderItems",orderItems);

        //重定向到order_details.jsp页面中
        response.sendRedirect("pages/order/order_details.jsp");


    }
    protected void receiveOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1.获取订单号Id
        String orderId = request.getParameter("orderId");
        //2.更改订单Id的状态
        orderService.receiveOrder(orderId);

        //获取最新的订单状态情况 重定向到获取最新订单项servlet程序
        response.sendRedirect("orderServlet?action=queryMyOrders");
        // ---订单发货-->可以签收-->订单签收-->更改订单状态(1-->2)-订单状态显示为已签收

    }





}
