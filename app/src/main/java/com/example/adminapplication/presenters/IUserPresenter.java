package com.example.adminapplication.presenters;

import com.example.adminapplication.CaiDat;
import com.example.adminapplication.models.request.ChangePasswordRequest;
import com.example.adminapplication.models.request.ChangeUserRequest;
import com.example.adminapplication.models.request.CreateUserRequest;
import com.example.adminapplication.models.request.UserRequest;

public interface IUserPresenter {
    void login(UserRequest userRequest);
    void createUser(CreateUserRequest createUserRequest);
    void getAllUser();
    void changePassword(int id, ChangePasswordRequest changePasswordRequest);
    void getUser(int id);
    void updateUser(int id, ChangeUserRequest changeUserRequest);
}
