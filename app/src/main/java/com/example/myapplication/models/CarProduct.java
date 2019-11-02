package com.example.myapplication.models;

import java.io.Serializable;

public class CarProduct implements Serializable {
    public int id;
    public String name;
    public String imgUrl;
    public String description;
    public int price;
    public int productCategoryId;
    public int amount;
    public int totalPrice;

    public CarProduct(int id, String name, String imgUrl, String description, int price, int productCategoryId, int amount){
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.price = price;
        this.productCategoryId = productCategoryId;
        this.amount = amount;
        this.totalPrice = amount*price;
    }

}
