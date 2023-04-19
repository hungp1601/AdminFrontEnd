package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminapplication.models.User;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.UserPresenter;
import com.example.adminapplication.views.ResponseView;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class NapTienActivity extends AppCompatActivity  implements ResponseView {
    ImageView btnBack;
    UserPresenter userPresenter;
    UserAdapter adapter;

    RecyclerView recyclerListUser;

    List<User> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_nap_tien);

        btnBack = findViewById(R.id.btnBack);
        recyclerListUser = findViewById(R.id.recyclerListUser);
        listUser = new ArrayList<>();

        btnBack.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), Home.class);
            startActivity(intent);
        });

        getUserList();
    }

    void getUserList(){
        try {

            userPresenter = new UserPresenter(this);
            userPresenter.getAllUser();
        }
        catch(Exception e) {
            Toast.makeText(this, "Có lỗi xảy ra " + e, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onComplete(BaseResponse account) {
        if(account.getCode() == -1 ){
            Toast.makeText(this, account.getMessage(),Toast.LENGTH_LONG).show();
        }
        else {
            LinkedTreeMap<String, Object> t = (LinkedTreeMap<String, Object>) account.getData();


            List<LinkedTreeMap<String, Object>> items = (List<LinkedTreeMap<String, Object>>) t.get("items");

            if (items != null) {
                for(LinkedTreeMap<String, Object> obj:items) {
                    User user = new User(
                            Double.valueOf(String.valueOf(obj.get("Id"))).intValue(),
                            (String) obj.get("FullName"),
                            (String) obj.get("PhoneNumber"),
                            (String) obj.get("Email"),
                            (String) obj.get("PersonalNumber"),
                            (String) obj.get("Address"),
                            String.valueOf(obj.get("activate")).equals("1.0"),
                            Double.valueOf(String.valueOf(obj.get("Coin"))).intValue(),
                            (String) obj.get("BirthDay")
                    );

                    listUser.add(user);
                }
                recyclerListUser.setLayoutManager(new LinearLayoutManager(this));

                adapter = new UserAdapter(this, listUser);
                recyclerListUser.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }
}