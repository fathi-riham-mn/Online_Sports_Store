package com.riham.sanaabilstore.models;

import java.io.Serializable;

public class ShowAllModel implements Serializable {

    String img_url;
    String name;
    String description;
    String rating;
    int price;
    String type;

    public ShowAllModel() {
    }

    public ShowAllModel(String img_url, String name, String description, String rating, int price, String type) {
        this.img_url = img_url;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
