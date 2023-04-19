package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminapplication.models.request.UserRequest;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.UserPresenter;
import com.example.adminapplication.views.ResponseView;
import com.google.gson.internal.LinkedTreeMap;

public class MainActivity extends AppCompatActivity implements ResponseView {
    Button btnLogin;
    EditText txtUserName, txtPassword;

    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(v -> {
            try {
                String userName = txtUserName.getText().toString();
                String passWord = txtPassword.getText().toString();
                UserRequest userRequest = new UserRequest(userName,passWord);

                userPresenter = new UserPresenter(this);

                userPresenter.login(userRequest);

            }
            catch(Exception e) {
                Toast.makeText(this, "Có lỗi xảy ra " + e, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onComplete(BaseResponse baseResponse) {
        if(baseResponse.getCode() == -1 ){
            Toast.makeText(this, baseResponse.getMessage(),Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent;
            intent = new Intent(this, Home.class);
            LinkedTreeMap<String, Double> obj = (LinkedTreeMap<String, Double>) baseResponse.getData();
            int id = obj.get("Id").intValue();
            intent.putExtra("id",id);
            startActivity(intent);
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}