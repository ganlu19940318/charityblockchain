package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class HarvestReq {
    @ApiModelProperty(value = "用户token", required = true)
    private String token;
    @ApiModelProperty(value = "土地编号", required = true)
    private int number;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "HarvestReq{" +
                "token='" + token + '\'' +
                ", number=" + number +
                '}';
    }
}
