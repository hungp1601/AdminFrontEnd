package com.example.adminapplication.models.request;

public class ChangePasswordRequest {
    String old_password, new_password;

    public ChangePasswordRequest(String old_password, String new_password) {
        this.old_password = old_password;
        this.new_password = new_password;
    }
}
