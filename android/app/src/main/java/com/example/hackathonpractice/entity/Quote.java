package com.example.hackathonpractice.entity;

import java.io.Serializable;

public class Quote implements Serializable {

    private int id;
    private String text;
    private String author;
    private int user_id;

    private String imgaeheart;

    private String edit;

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getImgaeheart() {
        return imgaeheart;
    }

    public void setImgaeheart(String imgaeheart) {
        this.imgaeheart = imgaeheart;
    }

    public Quote() {
    }

    public Quote(int id, String text, String author, int user_id, String imgaeheart, String edit) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.user_id = user_id;
        this.imgaeheart = imgaeheart;
        this.edit = edit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", user_id=" + user_id +
                ", imgaeheart='" + imgaeheart + '\'' +
                ", edit='" + edit + '\'' +
                '}';
    }
}
