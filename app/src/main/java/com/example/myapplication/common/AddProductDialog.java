package com.example.myapplication.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import com.example.myapplication.R;
import com.example.myapplication.models.CarProduct;
import com.example.myapplication.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddProductDialog extends DialogFragment {

    private AlertDialog.Builder builder;
    private View view;
    private Product product;
    private EditText amount;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        // Use the Builder class for convenient dialog construction
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.modal_title));
        if(isProduct()){
            builder.setNeutralButton(getString(R.string.modal_delete), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    try {
                        removeProduct();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        builder.setPositiveButton(getString(R.string.buy), new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                amount = (EditText) view.findViewById(R.id.editTextAmount);
                int amountNumber = 0;
                if(Validator.isInteger(amount.getText().toString())){
                    amountNumber = Integer.parseInt(amount.getText().toString());
                }
                try {
                    setCarProduct(amountNumber);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // FIRE ZE MISSILES!
            }
        })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        EditText mmm = (view.findViewById(R.id.editTextAmount));
        mmm.setText("1");
        builder.setView(view);
        return builder.create();
    }

    public void  setView(View view, Product product){
        this.view = view;
        this.product = product;
        //builder.setView(view);
    }
    private void setCarProduct(int amount) throws IOException {

        List<CarProduct> products = new ArrayList<>();
        CarProduct productToAdd = new CarProduct(product.id,
                product.name, product.imgUrl,
                product.description,
                product.price,
                product.productCategoryId,
                amount);
        try {
            products= IOStream.readObjects(view.getContext(),IOStream.carFile);

            int position = searchProduct(products, product);
            if(position !=-1){
                products.remove(position);
            }
            if(amount!=0){
                products.add(productToAdd);
            }

            IOStream.writeoObjects(view.getContext(),products,IOStream.carFile);

        } catch (IOException | ClassNotFoundException e) {

            products = new ArrayList<>();
            if(amount!=0){
                products.add(productToAdd);
            }
            IOStream.writeoObjects(view.getContext(),products,IOStream.carFile);
            e.printStackTrace();
        }


    }
    private int searchProduct(List<CarProduct> products, Product product){
        int target = -1;
        for(int i = 0; i < products.size();i++){
            if(products.get(i).id==product.id){
                target = i;
                break;
            }
        }
        return  target;
    }

    private void removeProductFromObj(List<CarProduct> products, Product product){
        int position = searchProduct(products, product);
        if(position!=-1){
            products.remove(position);
        }
    }
    private  void removeProduct() throws IOException, ClassNotFoundException {
        List<CarProduct> products = new ArrayList<>();
        products= IOStream.readObjects(view.getContext(),IOStream.carFile);
        removeProductFromObj(products,product);
        IOStream.writeoObjects(view.getContext(),products,IOStream.carFile);
    }
    //produc is in products ?
    private  boolean isProduct() {
        boolean result = false;
        if(IOStream.isFile(IOStream.carFile,view.getContext())){
            List<CarProduct> products = new ArrayList<>();
            try {
                products= IOStream.readObjects(view.getContext(),IOStream.carFile);
                if(searchProduct(products,product)!=-1){
                    result = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        return result;
    }




}