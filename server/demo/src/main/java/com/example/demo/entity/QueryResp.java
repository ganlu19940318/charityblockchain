package com.example.demo.entity;


public class QueryResp {
    private String address;
    private String name;
    private String info;
    private long money;
    private String donateaddress;
    private String state;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getDonateaddress() {
        return donateaddress;
    }

    public void setDonateaddress(String donateaddress) {
        this.donateaddress = donateaddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
