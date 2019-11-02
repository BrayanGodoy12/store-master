package com.example.myapplication.api;

import android.content.Context;
import android.content.Intent;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.activities.LoginActivity;
import com.example.myapplication.models.Login;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    private static RequestQueue queue;
    private static StringRequest stringGetRequest;
    private static StringRequest stringPostRequest;
    public static void httpGet(final Context self, String url, final ApiListerner apiListerner){



        queue = Volley.newRequestQueue(self);

        stringGetRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            apiListerner.onResponse(response);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 401) {
                            try {
                                Api.setTOKEN("",self);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Intent login = (Intent) new Intent(self, LoginActivity.class);
                            self.startActivity(login);
                        }else{
                            apiListerner.onErrorResponse(error);
                        }


                    }
                }){
            @Override
            public Map<String, String> getHeaders(){
                final Map<String, String> headers = new HashMap<>();
                try {
                    headers.put("Authorization", "Bearer " + Api.getTOKEN(self));//put your token here
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return headers;
            }
        };

        queue.add(stringGetRequest);
    }

    public static void httpPost(Context self, String url, final Login login, final ApiListerner apiListerner){
        queue = Volley.newRequestQueue(self);
        stringPostRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            apiListerner.onResponse(response);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        apiListerner.onErrorResponse(error);

                    }
                }){
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<>();
                    params.put("phone_number", login.phoneNumber);
                    params.put("document_number", login.documentNumber);
                    params.put("document_type", login.documentType);

                    return params;
                }
        };

        queue.add(stringPostRequest);
    }
}


