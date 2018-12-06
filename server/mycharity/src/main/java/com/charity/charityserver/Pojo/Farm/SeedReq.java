package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class SeedReq {
    @ApiModelProperty(value = "用户token", required = true)
    private String token;

    @ApiModelProperty(value = "获取种子的方式,1代表分享,2代表查看企业排行榜,3代表查看个人排行榜,4代表查看慈善排行榜", required = true)
    private String method;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "SeedReq{" +
                "token='" + token + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
