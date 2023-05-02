package com.example.adminapplication.presenters;

import com.example.adminapplication.CaiDat;
import com.example.adminapplication.models.request.AddMoneyRequest;
import com.example.adminapplication.models.request.ChangePasswordRequest;
import com.example.adminapplication.models.request.ChangeUserRequest;
import com.example.adminapplication.models.request.CreateUserRequest;
import com.example.adminapplication.models.request.RegisterPlateRequest;
import com.example.adminapplication.models.request.UserRequest;

import okhttp3.MultipartBody;
import retrofit2.http.Part;

public interface IUserPresenter {
    void login(UserRequest userRequest);
    void createUser(CreateUserRequest createUserRequest);
    void getAllUser();
    void changePassword(int id, ChangePasswordRequest changePasswordRequest);
    void getUser(int id);
    void updateUser(int id, ChangeUserRequest changeUserRequest);
    void addMoney(int id, AddMoneyRequest addMoneyRequest);

    void registerPlate(int id, MultipartBody.Part image);

    void getPlates(int id);

    void updatePlate(int id, MultipartBody.Part image);

}
