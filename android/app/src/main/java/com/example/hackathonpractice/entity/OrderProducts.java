package com.example.hackathonpractice.entity;

import java.io.Serializable;

public class OrderProducts implements Serializable {

    int id;
    int userId;
    String statusId;
    int voucherId;
    int addressUserId;
    String note;
    int isPaymentOnline;
    String codeOrder;
    public OrderProducts() {
    }

    public OrderProducts(int id, int userId, String statusId, int voucherId, int addressUserId, String note, int isPaymentOnline, String codeOrder) {
        this.id = id;
        this.userId = userId;
        this.statusId = statusId;
        this.voucherId = voucherId;
        this.addressUserId = addressUserId;
        this.note = note;
        this.isPaymentOnline = isPaymentOnline;
        this.codeOrder = codeOrder;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getAddressUserId() {
        return addressUserId;
    }

    public void setAddressUserId(int addressUserId) {
        this.addressUserId = addressUserId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIsPaymentOnline() {
        return isPaymentOnline;
    }

    public void setIsPaymentOnline(int isPaymentOnline) {
        this.isPaymentOnline = isPaymentOnline;
    }

    @Override
    public String toString() {
        return "OrderProducts{" +
                "id=" + id +
                ", userId=" + userId +
                ", statusId='" + statusId + '\'' +
                ", voucherId=" + voucherId +
                ", addressUserId=" + addressUserId +
                ", note='" + note + '\'' +
                ", isPaymentOnline=" + isPaymentOnline +
                '}';
    }
}
