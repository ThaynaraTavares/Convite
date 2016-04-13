package com.example.ifpb.evento.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ifpb.evento.R;

public class SplashActivity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler SplashScreen = new Handler();
        SplashScreen.postDelayed(SplashActivity.this, 3000);
    }

    @Override
    public void run() {
        Intent intent = new Intent(this, MenuActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
