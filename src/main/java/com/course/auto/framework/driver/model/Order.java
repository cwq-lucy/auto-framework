package com.course.auto.framework.driver.model;

public class Order {
    private String id;
    private String orderId;
    private Integer amount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
