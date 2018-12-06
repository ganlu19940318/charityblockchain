package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class MergeReq {
    @ApiModelProperty(value = "用户token", required = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "MergeReq{" +
                "token='" + token + '\'' +
                '}';
    }
}
