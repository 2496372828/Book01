package com.sdjtxy.test;

import com.sdjtxy.dao.OrderDao;
import com.sdjtxy.dao.impl.OrderDaoImpl;

import com.sdjtxy.pojo.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    @Test
    void saveOrder() {
        OrderDao orderDao=new OrderDaoImpl();

        Order order=new Order("200853001",new Date(),new BigDecimal("80"),0,1);
        orderDao.saveOrder(order);
    }
    @Test
    void queryAllOrders(){
        OrderDao orderDao=new OrderDaoImpl();
        List<Order> orders = orderDao.queryAllOrders();
        System.out.println(orders);
    }
    @Test
    void queryOrderByUserId(){
        OrderDao orderDao=new OrderDaoImpl();
        List<Order> orders = orderDao.queryOrderByUserId(1);
        System.out.println(orders);
    }

}