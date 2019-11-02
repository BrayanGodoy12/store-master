package com.example.myapplication.models;

import java.io.Serializable;

public class OrderData implements Serializable {
    public String date;
    public int price;
    public String orderId;

    public  OrderData(String date, int price, String orderId){
       this.date = date;
       this.price = price;
       this.orderId = orderId;
    }
}
