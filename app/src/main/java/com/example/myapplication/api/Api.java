package com.example.myapplication.api;

import android.content.Context;
import com.example.myapplication.common.IOStream;
import com.example.myapplication.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Api {
    //private static String host = "http://580b5f4d.ngrok.io";
    private static String host = "http://192.168.0.4:3000";
    static String api = host+"/api";
    static String img = host+"/images";
    private static String TOKEN = "_token";

    public static String img(String url){
        return img+url;
    }
    public static String api(String url){
        return  api+url;
    }

    public static void setTOKEN(String _token,Context context) throws IOException {

        FileOutputStream fos =  context.getApplicationContext().openFileOutput(TOKEN, Context.MODE_PRIVATE);
        fos.write(_token.getBytes());
        fos.close();
    }

    public static  String getTOKEN(Context context) throws IOException {
        FileInputStream in = context.getApplicationContext().openFileInput(TOKEN);
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        String _token = sb.toString();
        in.close();
        return _token;
    }
    public  static  void  logout(Context context) throws IOException {
        setTOKEN("",context);
    }
    public static String getUserId(Context context) throws IOException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        users = IOStream.readObjects(context,IOStream.userFile);
        if(users.size()>0){
            User user = users.get(0);
            return user.id;
        }else{
            return "";
        }
    }

}
