package com.example.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CategoriesAdapter;
import com.example.myapplication.api.Api;
import com.example.myapplication.api.ApiListerner;
import com.example.myapplication.api.ApiRequest;
import com.example.myapplication.common.Capa;
import com.example.myapplication.handelers.ErrorHandle;
import com.example.myapplication.models.Category;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener { public static final String EXTRA_MESSAGE ="" ;
        private RecyclerView recyclerView;
        private RecyclerView.Adapter categoryAdapter;
        private RecyclerView.LayoutManager layoutManager;
        private Context self;
        private List<Category> categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        self = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Capa.capa(self, CarActivity.class);
//                Snackbar.make(view, "Replace with your own action_1", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        --------------------


        categories = new ArrayList<>();

        recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a Grid layout manager with 2 columns
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        categoryAdapter = new CategoriesAdapter(categories);
        recyclerView.setAdapter(categoryAdapter);



        ApiRequest.httpGet(this, Api.api("/categories"),new ApiListerner() {
            @Override
            public void onResponse(String response) throws JSONException {
                printCategories(response);

            }
            public  void onErrorResponse(VolleyError error){
                ErrorHandle.connerction(self);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
    public  void goToProducts(View view){

        //get id from category
        CardView iv = view.findViewById(R.id.cv);
        String categoryId=iv.getTag().toString();
        Intent intent = new Intent(this, ProductsActivity.class);

        //send category id to product activity
        intent.putExtra(EXTRA_MESSAGE, categoryId);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

    }
    //proccess response and print cardViews into recyclerView
    void printCategories(String response) throws JSONException {

        JSONArray categoryReader = new JSONArray(response);

        for(int i = 0; i < categoryReader.length(); i++){
            JSONObject categoryObject = categoryReader.getJSONObject(i);

            //add category object to Category list
            categories.add(new Category(categoryObject.getString("name"), Api.img(categoryObject.getString("img")),categoryObject.getInt("id")));


        }
        categoryAdapter = new CategoriesAdapter(categories);
        recyclerView.setAdapter(categoryAdapter);
    }
//    public void onBackPressed() {
//
//        Intent home  = (Intent) new Intent(this, HomeActivity.class);
//        startActivity(home);
//        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
//    }



}
