package com.sdjtxy.dao;

import com.sdjtxy.pojo.Order;

import java.util.List;

/**
 * OrderDao中应该有：
 *      1.保存订单
 *
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return
     */
    public int saveOrder(Order order);

    public List<Order> queryAllOrders();


    void sendOrder(String orderId);

    List<Order> queryOrderByUserId(int userId);

    void receiveOrder(String orderId);
}
