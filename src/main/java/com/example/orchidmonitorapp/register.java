package com.example.orchidmonitorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void addNewUser(View view){

        MyDbConn dbHandler = new MyDbConn(this, null, null, 1);

        EditText name = (EditText) findViewById(R.id.name);
        EditText surname = (EditText) findViewById(R.id.surname);
        EditText email = (EditText) findViewById(R.id.email);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText rePassword = (EditText) findViewById(R.id.re_password);


        if ( password.getText().toString().equals(rePassword.getText().toString()) ){

            dbHandler.addNewUser(name.getText().toString(),
                    surname.getText().toString(),
                    email.getText().toString(),
                    username.getText().toString(),
                    password.getText().toString()  );

            Toast.makeText(getApplicationContext(),
                    "Successfully registered!", Toast.LENGTH_SHORT).show();

            goLoginPage(view);

        }
        else{
            Toast.makeText(getApplicationContext(), "Passwords not matching", Toast.LENGTH_LONG).show();
        }

    }

    public void goLoginPage(View v){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);

    }

}