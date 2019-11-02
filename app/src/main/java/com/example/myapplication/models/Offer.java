package com.example.myapplication.models;

public class Offer {
    public int id;
    public String name;
    public String imgUrl;
    public String description;
    public int price;
    public int productCategoryId;

    public Offer(int id, String name, String imgUrl, String description, int price, int productCategoryId){
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.price = price;
        this.productCategoryId = productCategoryId;
    }
}
