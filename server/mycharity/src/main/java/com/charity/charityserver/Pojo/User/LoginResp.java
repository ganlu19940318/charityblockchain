package com.charity.charityserver.Pojo.User;

import io.swagger.annotations.ApiModelProperty;

public class LoginResp {
    @ApiModelProperty(value = "token,登录成功后的所有操作需要带上token,用于验证和身份标识")
    private String token;
    @ApiModelProperty(value = "用户类型, 3代表企业用户, 4代表个人用户")
    private String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LoginResp{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
