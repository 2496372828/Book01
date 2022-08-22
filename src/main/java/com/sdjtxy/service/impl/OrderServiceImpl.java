package com.sdjtxy.service.impl;

import com.sdjtxy.dao.BookDao;
import com.sdjtxy.dao.OrderDao;
import com.sdjtxy.dao.OrderItemDao;
import com.sdjtxy.dao.impl.BookDaoImpl;
import com.sdjtxy.dao.impl.OrderDaoImpl;
import com.sdjtxy.dao.impl.OrderItemDaoImpl;
import com.sdjtxy.pojo.*;
import com.sdjtxy.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {

        //先将购物车订单添加 再添加订单中的商品项
        //先添加订单  订单号===唯一性
        //订单号的生成-----时间戳+用户id(考虑到双11等火爆事件，在同一时间内可能出现大量订单)
        String orderId=System.currentTimeMillis()+""+userId;
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        for(Map.Entry<Integer,CartItem> cartItem:cart.getItems().entrySet()){
            //添加订单中的商品项
            //购物车商品项---->订单商品项
            OrderItem orderItem=new OrderItem(null,cartItem.getValue().getName(),cartItem.getValue().getCount(),
                    cartItem.getValue().getPrice(),cartItem.getValue().getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);

            //完成订单后更新库中书籍的实时销量和库存信息
            Book book = bookDao.queryBookById(cartItem.getKey());
            book.setSales(book.getSales()+cartItem.getValue().getCount());
            book.setStock(book.getStock()-cartItem.getValue().getCount());
            bookDao.updateBook(book);

        }
        return orderId;

    }

    @Override
    public List<Order> queryAllOrders() {
        List<Order> orders = orderDao.queryAllOrders();

        return orders;
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.sendOrder(orderId);
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderID(String orderId) {
        List<OrderItem> orderItems=orderItemDao.queryOrderItemsByOrderID(orderId);
        return orderItems;
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        List<Order> orders=orderDao.queryOrderByUserId(userId);
        return orders;
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.receiveOrder(orderId);
    }


}
