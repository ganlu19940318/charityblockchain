package com.charity.charityserver.Pojo.Rank;

import io.swagger.annotations.ApiModelProperty;

public class UserResp {

    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "poeid")
    private String poeid;
    @ApiModelProperty(value = "用户信息")
    private String info;
    @ApiModelProperty(value = "捐赠金额")
    private long money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoeid() {
        return poeid;
    }

    public void setPoeid(String poeid) {
        this.poeid = poeid;
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
        return "UserResp{" +
                "name='" + name + '\'' +
                ", poeid='" + poeid + '\'' +
                ", info='" + info + '\'' +
                ", money=" + money +
                '}';
    }
}
