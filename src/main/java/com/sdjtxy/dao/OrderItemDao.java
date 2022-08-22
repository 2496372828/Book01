package com.sdjtxy.dao;

import com.sdjtxy.pojo.Order;
import com.sdjtxy.pojo.OrderItem;

import java.util.List;

/**
 * OrderDao中应该有：
 *      1.保存订单项
 *
 */
public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemsByOrderID(String orderId);
}
