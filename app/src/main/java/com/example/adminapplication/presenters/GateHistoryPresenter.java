package com.example.adminapplication.presenters;

import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.services.APIService;
import com.example.adminapplication.views.ResponseView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GateHistoryPresenter implements IGateHistoryPresenter {
    private ResponseView responseView;

    public GateHistoryPresenter(ResponseView responseView) {
        this.responseView = responseView;
    }

    @Override
    public void getAllGateHistory() {
        APIService.apiService.getAllGateHistory().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.body() != null){
                    responseView.onComplete(response.body());
                }else{
                    responseView.onError("null");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                responseView.onError(t.getMessage());
            }
        });
    }
}
