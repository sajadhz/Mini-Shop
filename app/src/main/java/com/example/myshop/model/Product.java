package com.example.myshop.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;
//Product model
@Entity
public class Product {
    @PrimaryKey
    @NonNull
    String id ;
    String name ;
    String url;
    double price ;
    double rawPrice ;
    int quantity;

    public Product() {
        this.id = UUID.randomUUID().toString();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(double rawPrice) {
        this.rawPrice = rawPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
