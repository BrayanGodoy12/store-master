package com.example.myapplication.models;

import android.content.Context;
import com.example.myapplication.common.Common;

import java.util.List;

public class Order {
    public  int id;
    public  List<CarProduct> products;

    public Order(List<CarProduct> products, int id){
        this.id = id;
        this.products = products;
    }

    public int getPrice(){
       return Common.getTotalPriceProductObject(products);
    }
}
