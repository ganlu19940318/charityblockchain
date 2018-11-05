package com.example.demo.entity;

public class ConfirmReq {
    private String poeid;
    private String address;
    private String pk64;
    // poeid
    private String useraddress;
    private long money;

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getPoeid() {
        return poeid;
    }

    public void setPoeid(String poeid) {
        this.poeid = poeid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPk64() {
        return pk64;
    }

    public void setPk64(String pk64) {
        this.pk64 = pk64;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    @Override
    public String toString() {
        return "ConfirmReq{" +
                "poeid='" + poeid + '\'' +
                ", address='" + address + '\'' +
                ", pk64='" + pk64 + '\'' +
                ", useraddress='" + useraddress + '\'' +
                '}';
    }
}
