package com.example.myapplication.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import com.example.myapplication.R;
import com.example.myapplication.activities.*;
import com.example.myapplication.api.Api;

import java.io.IOException;

public class Capa {
    public static void capa(Context context, Class activity){
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    public static boolean onNavigationItemSelected(MenuItem item, Activity activity){
        Context context = activity;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_categories) {
            // Handle the camera action
            Capa.capa(context, HomeActivity.class);
        } else if (id == R.id.nav_products) {
            Capa.capa(context, ProductsActivity.class);
        } else if (id == R.id.nav_car) {
            Capa.capa(context, CarActivity.class);
        } else if (id == R.id.nav_orders) {
            Capa.capa(context, OrdersActivity.class);
        } else if (id == R.id.nav_logout) {
            try {
                Api.logout(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Capa.capa(context, LoginActivity.class);
        }else if (id == R.id.nav_personal_data) {
            Capa.capa(context, UserActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
