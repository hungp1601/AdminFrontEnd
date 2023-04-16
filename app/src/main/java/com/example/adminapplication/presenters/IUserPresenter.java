package com.example.adminapplication.presenters;

import com.example.adminapplication.models.request.CreateUserRequest;
import com.example.adminapplication.models.request.UserRequest;

public interface IUserPresenter {
    void login(UserRequest userRequest);
    void createUser(CreateUserRequest createUserRequest);

    void getAllUser();
}
