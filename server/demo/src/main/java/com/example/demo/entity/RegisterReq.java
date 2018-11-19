package com.example.demo.entity;

import io.swagger.annotations.ApiParam;

public class RegisterReq {
    @ApiParam(value = "用户名", required = true)
    private String username;
    @ApiParam(value = "密码", required = true)
    private String password;
    @ApiParam(value = "类型,0代表机构,1代表用户", required = true)
    private String type;
    @ApiParam(value = "名称", required = true)
    private String name;
    @ApiParam(value = "基本信息", required = true)
    private String info;
    @ApiParam(value = "电话号码", required = true)
    private String tel;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    @Override
    public String toString() {
        return "RegisterReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
