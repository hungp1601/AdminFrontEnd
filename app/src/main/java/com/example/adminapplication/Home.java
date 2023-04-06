package com.example.adminapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    ImageView btnLogout;

    ImageButton btnNapTien, btnMoTK, btnCaiDat, btnLichSu, btnTDTTKH, btnTKDT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout = findViewById(R.id.btnLogout);

        // Find references to each ImageButton by their respective IDs
        btnNapTien = findViewById(R.id.btnNapTien);
        btnMoTK = findViewById(R.id.btnMoTK);
        btnCaiDat = findViewById(R.id.btnCaiDat);
        btnLichSu = findViewById(R.id.btnLichSu);
        btnTDTTKH = findViewById(R.id.btnTDTTKH);
        btnTKDT = findViewById(R.id.btnTKDT);

        btnNapTien.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), NapTienActivity.class);
            startActivity(intent);
        });

        btnMoTK.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), MoTaiKhoan.class);
            startActivity(intent);
        });

        btnCaiDat.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), CaiDat.class);
            startActivity(intent);
        });

        btnLichSu.setOnClickListener(v -> {
            // Handle click on LichSu button
            // ...
        });

        btnTDTTKH.setOnClickListener(v -> {
            // Handle click on TDTTKH button
            // ...
        });

        btnTKDT.setOnClickListener(v -> {
            // Handle click on TKDT button
            // ...
        });


        btnLogout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Logout Confirmation");
            builder.setMessage("Are you sure you want to log out?");

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Intent intent;
                intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "You have been logged out.", Toast.LENGTH_SHORT).show();
            });
            builder.setNegativeButton("No", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}