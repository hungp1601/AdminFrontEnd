package com.example.adminapplication.models.request;

import okhttp3.MultipartBody;

public class RegisterPlateRequest {
    MultipartBody.Part image;

    public RegisterPlateRequest(MultipartBody.Part image) {
        this.image = image;
    }
}
