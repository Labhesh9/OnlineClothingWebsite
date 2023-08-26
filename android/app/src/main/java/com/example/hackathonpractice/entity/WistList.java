package com.example.hackathonpractice.entity;

import java.io.Serializable;

public class WistList implements Serializable {
    int id;
    int productId;
    String imageProduct;
    String nameProduct;
    String descriptionProduct;
    int priceProduct;

    int userId;

    public WistList() {
    }

    public WistList(int id, int productId, String imageProduct, String nameProduct, String descriptionProduct, int priceProduct, int userId) {
        this.id = id;
        this.productId = productId;
        this.imageProduct = imageProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    @Override
    public String toString() {
        return "WistList{" +
                "id=" + id +
                ", productId=" + productId +
                ", imageProduct='" + imageProduct + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", descriptionProduct='" + descriptionProduct + '\'' +
                ", priceProduct=" + priceProduct +
                ", userId=" + userId +
                '}';
    }
}
