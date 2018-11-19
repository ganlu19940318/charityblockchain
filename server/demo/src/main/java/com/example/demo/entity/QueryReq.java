package com.example.demo.entity;

import io.swagger.annotations.ApiParam;

public class QueryReq {
    @ApiParam(value = "机构的address,不是poeid", required = true)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
