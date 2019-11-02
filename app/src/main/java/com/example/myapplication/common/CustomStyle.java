package com.example.myapplication.common;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

public class CustomStyle {
    public static void setBackgroundColor(View view, String color){
        view.setBackgroundColor(Color.parseColor(color));
    }
    public static void alternateBackgroundColor(View view, String color1, String color2){
        /**
         * chceck if back groun color has been seted and set it
         *
         * */
        ColorDrawable colorDrawable = ((ColorDrawable) view.getBackground());

        if(colorDrawable == null) {
            CustomStyle.setBackgroundColor(view, color1);
            return;
        }
        int intColor;
        String hexColor;
        intColor = colorDrawable.getColor();
        hexColor = String.format("#%06X", (0xFFFFFF & intColor));

        if(!hexColor.equals(color1)){
            CustomStyle.setBackgroundColor(view, color1);
        }else{
            CustomStyle.setBackgroundColor(view, color2);
        }


    }

}