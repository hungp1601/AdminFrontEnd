package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapplication.models.Car;
import com.example.adminapplication.models.User;
import com.example.adminapplication.models.request.AddMoneyRequest;
import com.example.adminapplication.models.request.ChangeUserRequest;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.UserPresenter;
import com.example.adminapplication.views.ResponseView;

import java.util.List;

public class NapTienUser extends AppCompatActivity implements ResponseView {

    UserPresenter userPresenter;
    User user;
    TextView txtMaTK, txtSodu, txtTienOne,txtTienTwo, txtTienThree, txtTienFour, txtTienFive, txtTienSix;

    EditText txtTienNap;

    AppCompatButton btnNapTien;
    ImageView btnBack;
    List<Car> listCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_tien_user);

        txtMaTK = findViewById(R.id.txtMaTK);
        txtSodu = findViewById(R.id.txtSodu);
        txtTienOne = findViewById(R.id.txtTienOne);
        txtTienTwo = findViewById(R.id.txtTienTwo);
        txtTienThree = findViewById(R.id.txtTienThree);
        txtTienFour = findViewById(R.id.txtTienFour);
        txtTienFive = findViewById(R.id.txtTienFive);
        txtTienSix = findViewById(R.id.txtTienSix);
        txtTienNap = findViewById(R.id.txtTienNap);
        btnNapTien = findViewById(R.id.btnNapTien);
        btnBack = findViewById(R.id.btnBack);

        fetchUser();
        setBtnSelectMoney();
        setBtnNapTien();

        btnBack.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), NapTienActivity.class);
            startActivity(intent);
        });
    }

    private void fetchUser() {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("clicked_user");

        txtSodu.setText(user.getCoin().toString() + " đ");
        txtMaTK.setText(user.getEmail());
    }

    private void setBtnSelectMoney(){
        View.OnClickListener tienClickListener = v -> {
            String tagValue = v.getTag().toString();
            txtTienNap.setText(tagValue);
        };

        txtTienOne.setOnClickListener(tienClickListener);
        txtTienTwo.setOnClickListener(tienClickListener);
        txtTienThree.setOnClickListener(tienClickListener);
        txtTienFour.setOnClickListener(tienClickListener);
        txtTienFive.setOnClickListener(tienClickListener);
        txtTienSix.setOnClickListener(tienClickListener);
    }

    private void setBtnNapTien(){
        btnNapTien.setOnClickListener(v -> {
            if(txtTienNap.getText().toString().equals("")){
                Toast.makeText(this, "Please input the money", Toast.LENGTH_LONG).show();
                return ;
            }

            int tienNap =  Integer.parseInt(txtTienNap.getText().toString());

            if(tienNap<=0){
                Toast.makeText(this, "The money value is not invalid", Toast.LENGTH_LONG).show();
                return;
            }

            AddMoneyRequest addMoneyRequest =
                    new AddMoneyRequest(tienNap);
            userPresenter = new UserPresenter(this);
            userPresenter.addMoney(user.getId(), addMoneyRequest);
            txtTienNap.setText("");
        });


    }

    @Override
    public void onComplete(BaseResponse account) {
        if (account.getCode() == -1) {
            Toast.makeText(this, account.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            int tienNap = Integer.parseInt(txtTienNap.getText().toString()) + user.getCoin();

            txtSodu.setText(tienNap + " đ");
            user.setCoin(tienNap);
            Toast.makeText(this,"Add money successfully", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onComplete(BaseResponse account, String type) {

    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}