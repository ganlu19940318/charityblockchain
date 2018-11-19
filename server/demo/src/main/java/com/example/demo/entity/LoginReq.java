package com.example.demo.entity;

import io.swagger.annotations.ApiParam;

public class LoginReq {
    @ApiParam(value = "用户名", required = true)
    private String username;
    @ApiParam(value = "密码", required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
