package com.charity.charityserver.Pojo.User;

import io.swagger.annotations.ApiModelProperty;

public class CardReq {
    @ApiModelProperty(value = "token", required = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CardReq{" +
                "token='" + token + '\'' +
                '}';
    }
}
