package com.example.myapplication.api;

import android.widget.TextView;
import com.android.volley.VolleyError;
import org.json.JSONException;

import java.io.IOException;

public interface ApiListerner {
    public void onResponse(String response) throws JSONException, IOException;
    public void onErrorResponse(VolleyError error);
}
