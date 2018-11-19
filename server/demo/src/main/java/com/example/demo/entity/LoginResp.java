package com.example.demo.entity;

public class LoginResp {
    private String address;
    private String pk64;
    private String poeid;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPoeid() {
        return poeid;
    }

    public void setPoeid(String poeid) {
        this.poeid = poeid;
    }
}
