package com.example.myapplication.common;

import android.content.Context;
import com.example.myapplication.models.CarProduct;


import java.io.IOException;
import java.util.List;

public class Common {
    public static int getCartTotalPrice(Context context) throws IOException, ClassNotFoundException {
        int result = 0;
        List<CarProduct> products =  IOStream.readObjects(context, IOStream.carFile);
        result = getTotalPriceProductObject(products);
        return result;
    }
    public static int getTotalPriceProductObject(List<CarProduct> products){

        int result = 0;
        try{
            for(int i = 0; i< products.size(); i++ ){
                result += (products.get(i).amount*products.get(i).price);
            }
        }catch (Exception e){
            result = 0;
        }

        return  result;
    }
}
