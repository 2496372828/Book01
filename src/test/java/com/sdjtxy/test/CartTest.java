package com.sdjtxy.test;

import com.sdjtxy.pojo.Cart;
import com.sdjtxy.pojo.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addItem() {
        Cart cart=new Cart();
        CartItem cartItem=new CartItem(1,"java从入门到放弃",1,new BigDecimal(28.30),new BigDecimal(28.30));
        cart.addItem(cartItem);
        System.out.println(cart);

    }

    @Test
    void deleteItem() {
    }

    @Test
    void clear() {
    }

    @Test
    void updateCount() {
    }

    @Test
    void getTotalCount() {
    }

    @Test
    void getTotalPrice() {
    }

    @Test
    void getItems() {
    }

    @Test
    void setItems() {
    }

    @Test
    void testToString() {
    }
}