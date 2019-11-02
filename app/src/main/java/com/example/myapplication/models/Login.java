package com.example.myapplication.models;


public class Login {
    public  String phoneNumber;
    public  String documentNumber;
    public  String documentType;

    public Login(String phoneNumber,String documentNumber,String documentType){
        this.phoneNumber = phoneNumber;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
    }
}
