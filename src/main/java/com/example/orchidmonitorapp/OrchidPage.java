package com.example.orchidmonitorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrchidPage extends AppCompatActivity {

    String url= "https://api.thingspeak.com/channels/1622305/feeds.json?api_key=86W4RDSWB03A5M65&results=2";
    String urlLight = "https://api.thingspeak.com/channels/1633631/feeds.json?api_key=AGJ09VQYQMHQYZ8S&results=2";
    adapterOrcDetails adapterObj2;
    ArrayList<orchidDetails> datalist2;
    String field1;
    String field2;
    String field3;
    String commTemp;
    String commMoist;
    String commLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orchid_page);

        Intent intent = getIntent();
        String selectedOrchid = intent.getStringExtra("selectedOrchid");

        ((TextView)findViewById(R.id.orchidTitle)).setText(selectedOrchid);

        datalist2 = new ArrayList<>();

        sendRequest();

    }

    public void refreshBtn (View v){
        finish();
        startActivity(getIntent());

    }

    public void sendRequest(){

        //new request
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //get "feeds" array from JSON url
                            JSONArray jsonArray = response.getJSONArray("feeds");

                            //get fields from array
                            for (int i = jsonArray.length()-1; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                field1 = jsonObject.getString("field1");
                                field2 = jsonObject.getString("field2");
                                field3 = jsonObject.getString("field3");

                            }
                            calculateComm();

                            datalist2.add(new orchidDetails("MOISTURE LEVEL", field1, commMoist,  R.drawable.moist));
                            datalist2.add(new orchidDetails("TEMPERATURE", field2 + "°C", commTemp,  R.drawable.temp));
                            datalist2.add(new orchidDetails("LIGHT", field3, commLight,  R.drawable.sun));


                            RecyclerView recObj = findViewById(R.id.recyclerDetails);
                            recObj.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapterObj2 = new adapterOrcDetails(getApplicationContext(), datalist2);
                            recObj.setAdapter(adapterObj2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Couldn't get the JSON array\n" + e, Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Couldn't get the JSON url", Toast.LENGTH_LONG).show();

                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    public void calculateComm(){

        int f1 = Integer.parseInt(field1);
        float f2 = Float.parseFloat(field2);
        int f3 = Integer.parseInt(field3);

        //moisture
        if(f1<=600){
            commMoist = "Water your orchid! The soil is dry. \nRemember to soak the orchid for 5 minutes.";
        }
        else if(f1>601 && f1<680){
            commMoist = "The soil is getting dry but you don't need to water the orchid at the moment.";
        }
        else if(f1>681 && f1<750){
            commMoist = "The soil is pretty moist";
        }
        else {
            commMoist = "The soil is moist.\nYou don't need to worry about watering for a few days.";
        }


        //temperature
        if(f2 <= 16){
            commTemp = "It's too cold for your orchid! Move it in a warmer place.";
        }
        else{
            commTemp = "It's nice and warm here. \nYour orchid is going to be fine.";
        }


        //light
        if(f3 > 80){
            commLight = "It's bright here";
        }
        else{
            commLight = "Orchids prefer a bright environment. Over the night should be fine.";
        }



    }

}