package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtUserName, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(v -> {
            try {
                Intent intent;
                intent = new Intent(v.getContext(), Home.class);
                startActivity(intent);
            }
            catch(Exception e) {
                Toast.makeText(this, "Có lỗi xảy ra " + e, Toast.LENGTH_LONG).show();
            }
        });
    }
}