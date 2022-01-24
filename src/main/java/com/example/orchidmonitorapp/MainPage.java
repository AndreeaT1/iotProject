package com.example.orchidmonitorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {
    adapterMainPage adapterObj;
    ArrayList<Orchid> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        datalist = new ArrayList<>();
        //add image
        datalist.add(new Orchid("Phalaenopsis", "My living room orchid ", R.drawable.pheli));

        RecyclerView recObj = findViewById(R.id.recyclerMainPage);
        recObj.setLayoutManager(new LinearLayoutManager(this));
        adapterObj = new adapterMainPage(this, datalist);
        recObj.setAdapter(adapterObj);


    }
}