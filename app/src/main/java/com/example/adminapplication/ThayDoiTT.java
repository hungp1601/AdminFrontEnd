package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminapplication.models.User;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.UserPresenter;
import com.example.adminapplication.views.ResponseView;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ThayDoiTT extends AppCompatActivity implements ResponseView {

    ImageView btnBack;
    UserPresenter userPresenter;
    UserAdapter adapter;

    RecyclerView recyclerListUser;
    EditText txtTimKiem;

    List<User> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_tt);

        btnBack = findViewById(R.id.btnBack);
        recyclerListUser = findViewById(R.id.recyclerListUser);
        txtTimKiem = findViewById(R.id.txtTimKiem);
        listUser = new ArrayList<>();

        btnBack.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), Home.class);
            startActivity(intent);
        });

        getUserList();
        searchUserList(this);
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

    void searchUserList(Context context){
        txtTimKiem.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String searchText = txtTimKiem.getText().toString();


                List<User> filteredList = listUser.stream()
                        .filter(user -> user.getPhoneNumber().contains(searchText) ||
                                user.getFullName().matches(".*" + searchText + ".*") ||
                                user.getEmail().matches(".*" + searchText + ".*"))
                        .collect(Collectors.toList());

                recyclerListUser.setLayoutManager(new LinearLayoutManager(context));

                adapter = new UserAdapter(context, filteredList);
                recyclerListUser.setAdapter(adapter);
            }
        });
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