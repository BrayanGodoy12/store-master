package com.example.myapplication.common;

import android.content.Context;
import com.example.myapplication.models.CarProduct;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOStream {
    public static String carFile = "car_file";
    public static String productsFile = "products_file";
    public static String userFile = "user_file";

    public static void writeoObjects(Context context, List products, String file) throws IOException {
        FileOutputStream fos =  context.getApplicationContext().openFileOutput(file, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);


        for(int i =0; i<products.size(); i++){
            oos.writeObject(products.get(i));
        }

        oos.close();
    }


    public  static  List readObjects(Context context, String file) throws IOException, ClassNotFoundException {

        List objects = new ArrayList();
        FileInputStream fis = context.getApplicationContext().openFileInput(file);
        ObjectInputStream ois = new ObjectInputStream(fis);


        Object obj = ois.readObject();
        while (obj!=null){
            objects.add(obj);
            try {
                obj = ois.readObject();
            }catch (Exception e){
                break;
            }
        }
        ois.close();
        return  objects;
    }
    public static boolean isFile(String file, Context context){
        boolean result = false;
        try {
            FileInputStream fis = context.getApplicationContext().openFileInput(file);
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  result;
    }

}
