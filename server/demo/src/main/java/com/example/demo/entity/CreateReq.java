package com.example.demo.entity;

public class CreateReq {
    private String name;
    private String info;
    private long money;
    private String username;
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateReq{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", money=" + money +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
