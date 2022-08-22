package com.sdjtxy.dao.impl;

import com.sdjtxy.dao.OrderItemDao;
import com.sdjtxy.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {

        String sql="insert into t_order_item(name,price,total_price,count,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getCount(),orderItem.getOrderId());

    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderID(String orderId) {

        String sql="select id,name,price,total_price as totalPrice,order_id as orderId from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
