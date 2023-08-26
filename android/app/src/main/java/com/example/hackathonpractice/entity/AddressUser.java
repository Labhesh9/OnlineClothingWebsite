package com.example.hackathonpractice.entity;

import java.io.Serializable;

public class AddressUser implements Serializable {

    int id;
    String shipName;
    String shipAddress;
    String shipEmail;
    String shipPhoneNumber;
    int user_id;

    public AddressUser() {
    }

    public AddressUser(int id, String shipName, String shipAddress, String shipEmail, String shipPhoneNumber, int user_id) {
        this.id = id;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipEmail = shipEmail;
        this.shipPhoneNumber = shipPhoneNumber;
        this.user_id = user_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipEmail() {
        return shipEmail;
    }

    public void setShipEmail(String shipEmail) {
        this.shipEmail = shipEmail;
    }

    public String getShipPhoneNumber() {
        return shipPhoneNumber;
    }

    public void setShipPhoneNumber(String shipPhoneNumber) {
        this.shipPhoneNumber = shipPhoneNumber;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "AddressUser{" +
                "id=" + id +
                ", shipName='" + shipName + '\'' +
                ", shipAddress='" + shipAddress + '\'' +
                ", shipEmail='" + shipEmail + '\'' +
                ", shipPhoneNumber='" + shipPhoneNumber + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
