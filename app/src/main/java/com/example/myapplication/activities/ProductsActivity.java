package com.example.myapplication.activities;


import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.example.myapplication.adapters.ProductsAdapter;
import com.example.myapplication.api.Api;
import com.example.myapplication.api.ApiListerner;
import com.example.myapplication.api.ApiRequest;
import com.example.myapplication.common.AddProductDialog;
import com.example.myapplication.common.Capa;
import com.example.myapplication.common.CustomStyle;
import com.example.myapplication.common.IOStream;
import com.example.myapplication.handelers.ErrorHandle;
import com.example.myapplication.models.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter productAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context self;
    private List<Product> products;
    LayoutInflater inflater;
    View modalview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        self = this;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Capa.capa(self, CarActivity.class);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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

//        -----------------------------------------------------
        Intent intent = getIntent();
        String message = intent.getStringExtra(HomeActivity.EXTRA_MESSAGE);
        message = (message!=null)?message:"";


        products = new ArrayList<>();

        recyclerView = findViewById(R.id.offersRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a Grid layout manager with 2 columns
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        productAdapter = new ProductsAdapter(products);
        recyclerView.setAdapter(productAdapter);
        System.out.println(Api.api("/products/"+message));
        ApiRequest.httpGet(this, Api.api("/products/"+message),new ApiListerner() {

            @Override
            public void onResponse(String response) throws JSONException, IOException {
                printProducts(response);

            }
            public  void onErrorResponse(VolleyError error){
                ErrorHandle.connerction(self);

            }
        });
    }
    void printProducts(String response) throws JSONException, IOException {
        JSONArray productReader = new JSONArray(response);

        for(int i = 0; i < productReader.length(); i++){
            JSONObject productObject = productReader.getJSONObject(i);
            products.add(new Product(productObject.getInt("id"),productObject.getString("name"), Api.img(productObject.getString("img_url")),productObject.getString("description"),productObject.getInt("price"),productObject.getInt("product_category_id")));

        }
        IOStream.writeoObjects(this,products,IOStream.productsFile);
        productAdapter = new ProductsAdapter(products);
        recyclerView.setAdapter(productAdapter);
    }


    public void selectProduct(View view){
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        modalview = inflater.inflate(R.layout.popup_product, null);



        CustomStyle.alternateBackgroundColor(view, "#A9D0F5", "#f9f9f9");
        CardView cv = (CardView) view.getParent();
        int productId = Integer.parseInt(cv.getTag().toString());
        Product product = getActualProduct(products,productId);
//        System.out.println(product.id);


        AddProductDialog fragment = new AddProductDialog();
        fragment.setView(modalview,product);
        FragmentManager fragmentManager = getFragmentManager();

        fragment.show(fragmentManager, "popup_product");
    }
    private Product getActualProduct(List<Product> products,int id){
        Product result = null;
        for(int i = 0; i< products.size(); i++){
            if(products.get(i).id == id){
                result = products.get(i);
                break;
            }

        }
        return result;
    }
    public void onBackPressed() {

        Intent home  = (Intent) new Intent(this, HomeActivity.class);
        startActivity(home);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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

}
