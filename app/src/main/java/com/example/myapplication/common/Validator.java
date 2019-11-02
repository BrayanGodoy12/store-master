package com.example.myapplication.common;

import android.content.Context;
import com.example.myapplication.api.Api;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Validator {

    public static boolean isInteger(String numero){
        try{
            Long.parseLong(numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public static boolean isPhoneNumber(String phone) {

        //FIXME: validate the correct way to validate a phone number
        boolean result = Validator.isInteger(phone);
        if(result){
            return phone.length() ==10;
        }else{
            return false;
        }

    }
    public static boolean isPhoneNumber(long phone) {

        //FIXME: validate the correct way to validate a phone number
        return String.valueOf(phone).length()==10;

    }

    public  static  boolean isDocumentNumber(String documentNumber){
        //FIXME: validate the correct way to validate a document number
        boolean result = Validator.isInteger(documentNumber);
        if(result){
            return documentNumber.length() >6;
        }else{
            return false;
        }
    }
    public static boolean isPasswordValid(String password) {
        //FIXME: validate the correct way to validate a password
        return Validator.isDocumentNumber(password);
    }

    public static boolean validateLogin(String response) throws JSONException {
        //FIXME: validate token

        JSONObject loginResponse = new JSONObject(response);
        String _token=loginResponse.getString("_token");
        if(_token != "null"&&_token!=null){
            return  true;
        }
        return false;
    }

    public static boolean isLoged(Context context){
        //FIXME: validate login
        try {
            String _token = Api.getTOKEN(context);
            if(_token.length()>0){
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
