package com.sdjtxy.pojo;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 购物车对象
 */
public class Cart {


    /**
     * key--商品编号
     * value---商品对象
     */
    private Map<Integer,CartItem> items=new LinkedHashMap<>();

    public void addItem(CartItem item){
        //先查看购物车中是否存在该商品，有则数量增加，金额增加，无则添加
        CartItem cartItem=items.get(item.getId());
        if(cartItem==null){
            //购物车中不存在该商品
            items.put(item.getId(),item);
        }else{
            //购物车中存在该商品
            cartItem.setCount(item.getCount()+1);
            //multiply--适用于BigDecimal类型的乘法
            cartItem.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }


    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            //购物车中有该商品，更改商品总金额和商品数量
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    /**
     *
     * @return 返回购物车商品总数量
     */
    public Integer getTotalCount(){
        Integer totalCount=0;

        for(Map.Entry<Integer,CartItem> item:items.entrySet()){
            totalCount+=item.getValue().getCount();
        }
        return totalCount;
    }

    /**
     *
     * @return 得到购物车中商品金额总量
     */
    public BigDecimal getTotalPrice(){

        BigDecimal totalPrice= BigDecimal.valueOf(0);

        for(Map.Entry<Integer,CartItem> item:items.entrySet()){
            totalPrice=totalPrice.add(item.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}

















