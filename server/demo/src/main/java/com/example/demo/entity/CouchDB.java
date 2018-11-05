package com.example.demo.entity;

import java.util.ArrayList;

public class CouchDB {
    private String name;
    private String type;
    private String state;
    private String info;
    private long money;
    private ArrayList<Enty> charity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public ArrayList<Enty> getCharity() {
        return charity;
    }

    public void setCharity(ArrayList<Enty> charity) {
        this.charity = charity;
    }
}
