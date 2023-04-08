package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LichSu extends AppCompatActivity {
    LinearLayout linearRowlichsxeva, linearRowlichsgiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);
        linearRowlichsgiao = findViewById(R.id.linearRowlichsgiao);
        linearRowlichsxeva = findViewById(R.id.linearRowlichsxeva);

        linearRowlichsgiao.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), LichSuGiaoDich.class);
            startActivity(intent);
        });
        linearRowlichsxeva.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), LichSuXe.class);
            startActivity(intent);
        });
    }
}