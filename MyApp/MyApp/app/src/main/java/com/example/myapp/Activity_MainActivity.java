package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Activity_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(){

            @Override
            public void run(){
                super.run();
                try {
                    sleep(2000);
                    startActivity(new Intent(Activity_MainActivity.this, Activity_Login.class));
                    finish();
                }catch (Exception e){

                }
            }
        };thread.start();
    }
}