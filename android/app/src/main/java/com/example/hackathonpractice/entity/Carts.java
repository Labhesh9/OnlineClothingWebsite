package com.example.hackathonpractice.entity;

import java.io.Serializable;

public class Carts implements Serializable {

    int userId;
    int productId;
    int quantity;
    int  statusId ;

    int id;
    String name;
    int Price;
    String imageProduct;

    public Carts() {
    }

    public Carts(int userId, int productId, int quantity, int statusId, int id, String name, int price, String imageProduct) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.statusId = statusId;
        this.id = id;
        this.name = name;
        Price = price;
        this.imageProduct = imageProduct;
    }


    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", statusId=" + statusId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", Price=" + Price +
                ", imageProduct='" + imageProduct + '\'' +
                '}';
    }
}
