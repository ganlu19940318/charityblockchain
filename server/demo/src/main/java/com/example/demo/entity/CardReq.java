package com.example.demo.entity;

import io.swagger.annotations.ApiParam;

public class CardReq {
    // poeid
    @ApiParam(value = "用户的poeid", required = true)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
