package com.example.myapplication.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.example.myapplication.adapters.OrdersAdapter;
import com.example.myapplication.api.Api;
import com.example.myapplication.api.ApiListerner;
import com.example.myapplication.api.ApiRequest;
import com.example.myapplication.common.Capa;
import com.example.myapplication.handelers.ErrorHandle;
import com.example.myapplication.models.OrderData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Context self;
    private List<OrderData> orders;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter orderAdapter;
    private String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;
        setContentView(R.layout.activity_orders);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        -----------------------------------------------------------
        orders = new ArrayList<>();
        recyclerView = findViewById(R.id.ordersRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        orderAdapter = new OrdersAdapter(orders);
        recyclerView.setAdapter(orderAdapter);

        try {
            userId=(Api.getUserId(this));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        ApiRequest.httpGet(this, Api.api("/orders/"+userId), new ApiListerner() {
            @Override
            public void onResponse(String response) throws JSONException, IOException {
                printOrders(response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorHandle.connerction(self);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        return Capa.onNavigationItemSelected(item,this);
    }

//    print orders
    void printOrders(String response) throws JSONException {
        JSONArray orderReader = new JSONArray(response);
        for(int i = 0; i<orderReader.length();i++){
            JSONObject orderObject = orderReader.getJSONObject(i);
            orders.add(new OrderData(orderObject.getString("date"), Integer.parseInt(orderObject.getString("price")),orderObject.getString("order_id")));
        }
        orderAdapter = new OrdersAdapter(orders);
        recyclerView.setAdapter(orderAdapter);
    }

    public void selectOrder(View view){
        AlertDialog.Builder alert= new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("An order was clicked")
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

        alert.show();
    }

}
