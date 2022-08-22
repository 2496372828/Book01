package com.sdjtxy.dao.impl;

import com.sdjtxy.dao.OrderDao;
import com.sdjtxy.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    public int saveOrder(Order order){
        String sql="insert into t_order(order_id,create_time,total_price,status,user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateDate(),order.getPrice(),order.getStatus(),order.getUserId());


    }

    @Override
    public List<Order> queryAllOrders() {
        //查询所有订单
        String sql="select order_id as orderId,create_time as createDate,total_price as price,status,user_id as userId from t_order";
        List<Order> orders = queryForList(Order.class, sql);

        return orders;
    }

    @Override
    public void sendOrder(String orderId) {
        String sql="update  t_order set status = ? where order_id= ?";
        update(sql,1,orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {

        String sql="select order_id as orderId,create_time as createDate,total_price as price,status  from t_order where user_id=?";
        List<Order> orders = queryForList(Order.class, sql, userId);
        return orders;
    }

    @Override
    public void receiveOrder(String orderId) {
        String sql="update  t_order set status = ? where order_id= ?";
        update(sql,2,orderId);
    }
}
