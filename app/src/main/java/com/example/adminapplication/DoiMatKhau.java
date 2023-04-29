package com.example.adminapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminapplication.models.request.ChangePasswordRequest;
import com.example.adminapplication.models.request.UserRequest;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.UserPresenter;
import com.example.adminapplication.views.ResponseView;
import com.google.gson.internal.LinkedTreeMap;


public class DoiMatKhau extends AppCompatActivity  implements ResponseView {
    ImageView btnBack;
    AppCompatButton btnDoiMatKhau;
    private LocalStorage localStorage;
    private UserPresenter userPresenter;

    EditText txtMKCU, txtMKMoi,txtNhapLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        btnBack = findViewById(R.id.btnBack);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
        txtMKCU = findViewById(R.id.txtMKCU);
        txtMKMoi = findViewById(R.id.txtMKMoi);
        txtNhapLai = findViewById(R.id.txtNhapLai);
        localStorage = new LocalStorage(this);


        btnBack.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), CaiDat.class);
            startActivity(intent);
        });

        btnDoiMatKhau.setOnClickListener(v -> {
            String MKCu = txtMKCU.getText().toString();
            String MKMoi = txtMKMoi.getText().toString();
            String NhapLai = txtNhapLai.getText().toString();
            if(MKMoi.equals(NhapLai)){
                try {
                    Integer id = localStorage.getInt("user_id",0);

                    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(MKCu,MKMoi);

                    userPresenter = new UserPresenter(this);
                    userPresenter.changePassword(id,changePasswordRequest);

                }
                catch(Exception e) {
                    Toast.makeText(this, "Có lỗi xảy ra " + e, Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this,
                        "Mật khẩu nhập lại không trùng với mật khẩu cũ", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onComplete(@NonNull BaseResponse baseResponse) {
        Toast.makeText(this, baseResponse.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }
}