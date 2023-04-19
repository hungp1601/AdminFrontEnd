package com.example.adminapplication.models;


public class User {
    private String FullName,PhoneNumber,Email,PersonalNumber,Address;
    private int Id;
    private Boolean activate;
    private Integer Coin;
    private String BirthDay;

    public User(Integer Id,String fullName, String phoneNumber, String email,  String personalNumber
            , String address, Boolean activate, Integer coin, String birthDay) {
        this.Id = Id;
        FullName = fullName;
        PhoneNumber = phoneNumber;
        Email = email;
        PersonalNumber = personalNumber;
        Address = address;
        this.activate = activate;
        Coin = coin;
        BirthDay = birthDay;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPersonalNumber() {
        return PersonalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        PersonalNumber = personalNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public Integer getCoin() {
        return Coin;
    }

    public void setCoin(Integer coin) {
        Coin = coin;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
