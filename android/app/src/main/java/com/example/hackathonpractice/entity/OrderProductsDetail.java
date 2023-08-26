package com.example.hackathonpractice.entity;

import java.io.Serializable;

public class OrderProductsDetail implements Serializable {

    int id;
    int orderId;
    int productId;
    int quantity;
    int realPrice;

    int originalPrice;

    String name;

    String imageproduct;

    public OrderProductsDetail() {
    }

    public OrderProductsDetail(int id, int orderId, int productId, int quantity, int realPrice, int originalPrice, String name, String imageproduct) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.realPrice = realPrice;
        this.originalPrice = originalPrice;
        this.name = name;
        this.imageproduct = imageproduct;
    }


    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageproduct() {
        return imageproduct;
    }

    public void setImageproduct(String imageproduct) {
        this.imageproduct = imageproduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(int realPrice) {
        this.realPrice = realPrice;
    }

    @Override
    public String toString() {
        return "OrderProductsDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", realPrice=" + realPrice +
                ", originalPrice=" + originalPrice +
                ", name='" + name + '\'' +
                ", imageproduct='" + imageproduct + '\'' +
                '}';
    }
}
