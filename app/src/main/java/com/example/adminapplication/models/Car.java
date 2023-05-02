package com.example.adminapplication.models;

import java.io.Serializable;

public class Car implements Serializable {
    int Id,UserId;
    String NumberPlate,ImagePath;

    public Car(int id, int userId, String numberPlate, String imagePath) {
        Id = id;
        UserId = userId;
        NumberPlate = numberPlate;
        ImagePath = imagePath;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getNumberPlate() {
        return NumberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        NumberPlate = numberPlate;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
