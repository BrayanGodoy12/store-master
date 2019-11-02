package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.myapplication.activities.HomeActivity;
import com.example.myapplication.activities.LoginActivity;
import com.example.myapplication.common.Validator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        setTimeout(()->{
            if(Validator.isLoged(this)){
                Intent home  = (Intent) new Intent(this, HomeActivity.class);

                startActivity(home);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }else{
                Intent login = (Intent) new Intent(this, LoginActivity.class);
                startActivity(login);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        },1000);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    public void start(View view){
        Intent home  = (Intent) new Intent(this, LoginActivity.class);

        startActivity(home);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);


    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();

            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
