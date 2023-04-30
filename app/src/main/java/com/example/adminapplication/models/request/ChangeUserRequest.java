package com.example.adminapplication.models.request;

public class ChangeUserRequest {
    private String PhoneNumber;
    private String Address;
    private boolean activate;

    public ChangeUserRequest(String PhoneNumber, String Address, boolean activate) {
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
        this.activate = activate;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
}
