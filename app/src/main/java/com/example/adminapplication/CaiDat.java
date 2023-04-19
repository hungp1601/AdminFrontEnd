package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CaiDat extends AppCompatActivity {

    ImageView btnBack;
    LinearLayout linearRowlock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);

        btnBack = findViewById(R.id.btnBack);
        linearRowlock = findViewById(R.id.linearRowlock);

        btnBack.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), Home.class);
            Intent myIntent = getIntent();
            Integer id = myIntent.getIntExtra("id",0);

            intent.putExtra("id",id);
            startActivity(intent);
        });

        linearRowlock.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), DoiMatKhau.class);
            startActivity(intent);
        });
    }
}