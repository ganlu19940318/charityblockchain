package com.example.demo.entity;

import io.swagger.annotations.ApiParam;

public class TradeReq {
    @ApiParam(value = "用户的poeid", required = true)
    private String poeid;
    @ApiParam(value = "用户的address", required = true)
    private String address;
    @ApiParam(value = "用户的pk64", required = true)
    private String pk64;
    @ApiParam(value = "对方用户的poeid", required = true)
    private String topoeid;
    // itemid
    @ApiParam(value = "itemid,也就是物品poeid", required = true)
    private String charitypoeid;

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

    public String getTopoeid() {
        return topoeid;
    }

    public void setTopoeid(String topoeid) {
        this.topoeid = topoeid;
    }

    public String getCharitypoeid() {
        return charitypoeid;
    }

    public void setCharitypoeid(String charitypoeid) {
        this.charitypoeid = charitypoeid;
    }

    @Override
    public String toString() {
        return "TradeReq{" +
                "poeid='" + poeid + '\'' +
                ", address='" + address + '\'' +
                ", pk64='" + pk64 + '\'' +
                ", topoeid='" + topoeid + '\'' +
                ", charitypoeid='" + charitypoeid + '\'' +
                '}';
    }
}
