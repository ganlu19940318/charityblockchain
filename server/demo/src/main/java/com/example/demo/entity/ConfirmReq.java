package com.example.demo.entity;

import io.swagger.annotations.ApiParam;

public class ConfirmReq {

    @ApiParam(value = "慈善机构物品(当前用户)的poeid", required = true)
    private String poeid;
    @ApiParam(value = "慈善机构(当前用户)的address", required = true)
    private String address;
    @ApiParam(value = "慈善机构(当前用户)的pk64", required = true)
    private String pk64;
    // poeid
    @ApiParam(value = "用户(目标用户)的poeid", required = true)
    private String useraddress;

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
