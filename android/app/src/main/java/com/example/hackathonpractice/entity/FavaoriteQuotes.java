package com.example.hackathonpractice.entity;

import java.io.Serializable;

public class FavaoriteQuotes implements Serializable {

    private int user_id;
    private int quote_id;

    public FavaoriteQuotes() {
    }

    public FavaoriteQuotes(int user_id, int quote_id) {
        this.user_id = user_id;
        this.quote_id = quote_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    @Override
    public String toString() {
        return "FavaoriteQuotes{" +
                "user_id=" + user_id +
                ", quote_id=" + quote_id +
                '}';
    }
}
