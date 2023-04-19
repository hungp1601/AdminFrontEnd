package com.example.adminapplication.views;

import com.example.adminapplication.models.response.BaseResponse;

public interface ResponseView {
    public void onComplete(BaseResponse account);

    public void onError(String message);

}
