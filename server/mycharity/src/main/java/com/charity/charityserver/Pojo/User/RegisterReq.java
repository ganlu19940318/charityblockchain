package com.charity.charityserver.Pojo.User;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class RegisterReq {
    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;

    @ApiModelProperty(value = "身份证号", required = true)
    private String idCard;

    @ApiModelProperty(value = "联系方式", required = true)
    private String tel;

    @ApiModelProperty(value = "用户类型, 3代表企业用户, 4代表个人用户", required = true)
    private String type;

    @ApiModelProperty(value = "用户简介", required = true)
    private String info;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码, 这里还是以前那个校验逻辑", required = true)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", tel='" + tel + '\'' +
                ", type='" + type + '\'' +
                ", info='" + info + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
