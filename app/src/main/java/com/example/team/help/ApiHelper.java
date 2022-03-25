package com.example.team.help;

import android.os.AsyncTask;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ApiHelper extends AsyncTask<Void, Void, String> {
    private String url;

    public ApiHelper(String url){
        this.url=url;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return getJSON(url);
    }

    @Override
    protected void onPostExecute(String strings) {
        super.onPostExecute(strings);
    }


    public String getJSON(String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (Exception ex) {
            return ex.toString();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    //disconnect error
                }
            }
        }
        return null;
    }
}
/*public class ApiHelper {
    private static String response;
    public static String getJSON(String url) {

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                response = string;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        return response;
    }
}*/
