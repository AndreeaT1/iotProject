package com.example.orchidmonitorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goLoginPage(View v){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);

    }

    public void goRegisterPage(View v){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);

    }
}