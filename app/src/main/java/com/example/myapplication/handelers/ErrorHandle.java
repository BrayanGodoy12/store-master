package com.example.myapplication.handelers;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;


public class ErrorHandle {
    public  static  void connerction(Context context){
//        new MaterialAlertDialogBuilder(context)
//                .setTitle("Error")
//                .setMessage("Network Connection has Failed")
//                .setPositiveButton("Ok", null)
//                .show();


        AlertDialog.Builder alert= new AlertDialog.Builder(context)
        .setTitle("Error")
        .setMessage("The connection has failed")
        .setPositiveButton("ok", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        alert.show();

    }
}
