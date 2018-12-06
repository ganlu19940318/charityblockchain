package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class UseitemReq {
    @ApiModelProperty(value = "用户token", required = true)
    private String token;
    @ApiModelProperty(value = "土地编号", required = true)
    private int number;
    @ApiModelProperty(value = "物品编号", required = true)
    private String itemid;

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

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    @Override
    public String toString() {
        return "UseitemReq{" +
                "token='" + token + '\'' +
                ", number=" + number +
                ", itemid='" + itemid + '\'' +
                '}';
    }
}
