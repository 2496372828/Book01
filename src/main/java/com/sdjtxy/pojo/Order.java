package com.sdjtxy.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    //订单号
    private String orderId;
    private Date createDate;
    private BigDecimal price;
    //订单状态默认为0：未发货 1：已发货 2：已签收
    private Integer status=0;
    private Integer userId=0;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Order() {
    }

    public Order(String orderId, Date createDate, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
