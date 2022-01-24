package com.example.orchidmonitorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginBtn(View v){

        EditText username = (EditText) findViewById(R.id.usernameTxt);
        EditText password = (EditText) findViewById(R.id.passwordTxt);

        MyDbConn dbObj = new MyDbConn(this, null, null, 1);

        if( dbObj.checkLogin(username.getText().toString(), password.getText().toString() ) ){

            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }

        else{
            Toast.makeText(getApplicationContext(), "Wrong login details!\nTry again", Toast.LENGTH_LONG).show();
        }

    }

    public void goRegisterPage(View v){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);

    }



}