package com.sdjtxy.service;

import com.sdjtxy.pojo.Cart;
import com.sdjtxy.pojo.Order;
import com.sdjtxy.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    public String createOrder(Cart cart,Integer userId);

    /**
     *
     * @return 返回所有的订单(管理员模式)
     */
    public List<Order> queryAllOrders();

    void sendOrder(String orderId);


    List<OrderItem> queryOrderItemsByOrderID(String orderId);

    List<Order> queryOrderByUserId(int userId);

    void receiveOrder(String orderId);
}
