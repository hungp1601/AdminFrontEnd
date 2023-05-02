package com.example.adminapplication.models.request;

public class AddMoneyRequest {
    int money;

    public AddMoneyRequest(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
