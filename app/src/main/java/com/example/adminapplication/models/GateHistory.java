package com.example.adminapplication.models;

public class GateHistory {
    private int Id, UserId;
    private String NumberPlate, CheckInDate, CheckOutDate, ImagePathCheckIn, ImagePathCheckOut;
    private float Coin;

    public GateHistory(int id, int userId, String numberPlate, String checkInDate, String checkOutDate, String imagePathCheckIn, String imagePathCheckOut, float coin) {
        Id = id;
        UserId = userId;
        NumberPlate = numberPlate;
        CheckInDate = checkInDate;
        CheckOutDate = checkOutDate;
        ImagePathCheckIn = imagePathCheckIn;
        ImagePathCheckOut = imagePathCheckOut;
        Coin = coin;
    }

    public int getId() {
        return Id;
    }

    public int getUserId() {
        return UserId;
    }

    public String getNumberPlate() {
        return NumberPlate;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public String getImagePathCheckIn() {
        return ImagePathCheckIn;
    }

    public String getImagePathCheckOut() {
        return ImagePathCheckOut;
    }

    public float getCoin() {
        return Coin;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setNumberPlate(String numberPlate) {
        NumberPlate = numberPlate;
    }

    public void setCheckInDate(String checkInDate) {
        CheckInDate = checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        CheckOutDate = checkOutDate;
    }

    public void setImagePathCheckIn(String imagePathCheckIn) {
        ImagePathCheckIn = imagePathCheckIn;
    }

    public void setImagePathCheckOut(String imagePathCheckOut) {
        ImagePathCheckOut = imagePathCheckOut;
    }

    public void setCoin(float coin) {
        Coin = coin;
    }
}
