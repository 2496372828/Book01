package com.sdjtxy.test;

import com.sdjtxy.dao.OrderItemDao;
import com.sdjtxy.dao.impl.OrderItemDaoImpl;
import com.sdjtxy.pojo.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemDaoTest {

    @Test
    void saveOrderItem() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        OrderItem orderItem=new OrderItem(null,"数据结构与算法",1,new BigDecimal(78.50),new BigDecimal(78.50),"200853001");
        orderItemDao.saveOrderItem(orderItem);
    }
    @Test
    void queryOrderItemsByOrderID(){
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderID("16490944125361");
        System.out.println(orderItems);
    }
}