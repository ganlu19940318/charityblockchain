package com.charity.charityserver.Pojo.Charity;

import io.swagger.annotations.ApiModelProperty;

public class CreateReq {

    @ApiModelProperty(value = "慈善物品名称", required = true)
    private String name;
    @ApiModelProperty(value = "慈善物品信息", required = true)
    private String info;
    @ApiModelProperty(value = "慈善物品价值(元)", required = true)
    private long money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "CreateReq{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", money=" + money +
                '}';
    }
}
