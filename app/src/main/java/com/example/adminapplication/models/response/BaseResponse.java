package com.example.adminapplication.models.response;

public class BaseResponse {

    private int code;
    private Object data;
    private String message;

    public BaseResponse(int code, Object data, String message){
        this.code = code;
        this.data= data;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}


