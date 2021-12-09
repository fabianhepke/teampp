package com.example.team.help;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class ApiHelper {
    public static String getResult(Context context, String url) {
        final String[] result = new String[1];
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        result[0] = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    throw new Exception("Kack api aufruf hat net geklappt" + error);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        queue.add(stringRequest);
        return result[0];
    }
}
