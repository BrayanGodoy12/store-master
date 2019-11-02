package com.example.myapplication.models;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String id;

    public User(String name, String id){
        this.name = name;
        this.id = id;
    }
}
