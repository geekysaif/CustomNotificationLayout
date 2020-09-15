package com.example.saif.notificationsampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TestActivity extends AppCompatActivity {
    ImageView img_accept,img_reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String str = getIntent().getExtras().getString("key");

        img_accept= findViewById(R.id.img_accept);
        img_reject= findViewById(R.id.img_reject);


        if(str.equals("accept")){
            img_accept.setVisibility(View.VISIBLE);
            img_reject.setVisibility(View.GONE);
        }else {
            img_accept.setVisibility(View.GONE);
            img_reject.setVisibility(View.VISIBLE);
        }
    }
}