package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminapplication.models.request.CreateUserRequest;
import com.example.adminapplication.models.request.UserRequest;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.UserPresenter;
import com.example.adminapplication.views.ResponseView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoTaiKhoan extends AppCompatActivity implements ResponseView {
    ImageView btnBack;

    androidx.appcompat.widget.AppCompatButton btnCreate;

    EditText txtTen, txtEmail,txtNgaySinh,txtSDT,txtDiaChi,txtCCCD;

    UserPresenter userPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo_tai_khoan);

        btnBack = findViewById(R.id.btnBack);

        btnCreate = findViewById(R.id.btnCreate);

        txtTen = findViewById(R.id.txtTen);
        txtEmail = findViewById(R.id.txtEmail);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        txtSDT = findViewById(R.id.txtSDT);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtCCCD = findViewById(R.id.txtCCCD);

        btnBack.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), Home.class);
            startActivity(intent);
        });

        btnCreate.setOnClickListener(v -> {
            String ten = txtTen.getText().toString();
            String sdt = txtSDT.getText().toString();
            String email = txtEmail.getText().toString();
            String cccd = txtCCCD.getText().toString();
            String diachi = txtDiaChi.getText().toString();
            String ngaysinh = txtNgaySinh.getText().toString();


            CreateUserRequest createUserRequest = new CreateUserRequest(ten, sdt, email,"123456", cccd, diachi, true, 0, ngaysinh);

            userPresenter = new UserPresenter(this);

            userPresenter.createUser(createUserRequest);
        });
    }

    @Override
    public void onComplete(BaseResponse baseResponse) {
        if(baseResponse.getCode() == -1 ){
            Toast.makeText(this, baseResponse.getMessage(),Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Create user successfully",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onComplete(BaseResponse account, String type) {

    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }
}