package com.sdjtxy.test;

import com.sdjtxy.pojo.Cart;
import com.sdjtxy.pojo.CartItem;
import com.sdjtxy.service.OrderService;
import com.sdjtxy.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        OrderService orderService=new OrderServiceImpl();
        Cart cart=new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        String orderId = orderService.createOrder(cart, 1);
        System.out.println("订单编号为："+orderId);

    }
}