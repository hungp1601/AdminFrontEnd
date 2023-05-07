package com.example.adminapplication.services;

import com.example.adminapplication.models.request.AddMoneyRequest;
import com.example.adminapplication.models.request.ChangePasswordRequest;
import com.example.adminapplication.models.request.ChangeUserRequest;
import com.example.adminapplication.models.request.CreateUserRequest;
import com.example.adminapplication.models.request.RegisterPlateRequest;
import com.example.adminapplication.models.request.UserRequest;
import com.example.adminapplication.models.response.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    MediaType mediaType = MediaType.parse("application/json");
    OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS) // Thiết lập timeout kết nối
            .readTimeout(60, TimeUnit.SECONDS) // Thiết lập timeout đọc dữ liệu
            .writeTimeout(60, TimeUnit.SECONDS) // Thiết lập timeout ghi dữ liệu
            .build();
    APIService apiService = new Retrofit.Builder()
            .baseUrl("http:192.168.1.102:8000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
            .create(APIService.class);

    @POST("/api/v1/users/login")
    Call<BaseResponse> login(@Body UserRequest userRequest);

    @POST("/api/v1/users")
    Call<BaseResponse> createUser(@Body CreateUserRequest createUserRequest);

    @GET("/api/v1/users")
    Call<BaseResponse> getAllUser();

    @PUT("/api/v1/users/{id}/updatePassword")
    Call<BaseResponse> changePassword(@Path("id")int id,@Body ChangePasswordRequest changePasswordRequest );

    @GET("/api/v1/users/{id}")
    Call<BaseResponse> getUser(@Path("id")int id);

    @PUT("/api/v1/users/{id}")
    Call<BaseResponse> updateUser(@Path("id")int id,@Body ChangeUserRequest changeUserRequest );

    @PUT("/api/v1/users/{id}/addMoney")
    Call<BaseResponse> addMoney(@Path("id")int id,@Body AddMoneyRequest addMoneyRequest);

    @Multipart
    @POST("/api/v1/plates/{id}")
    Call<BaseResponse> registerPlate(@Path("id")int id,@Part MultipartBody.Part image);

    @GET("/api/v1/plates")
    Call<BaseResponse> getPlates(@Query("user_id")int userId);

    @Multipart
    @PUT("/api/v1/plates/{id}")
    Call<BaseResponse> updatePlate(@Path("id")int id,@Part MultipartBody.Part image);

    @GET("/api/v1/gate_histories/all/")
    Call<BaseResponse> getAllGateHistory();
}

