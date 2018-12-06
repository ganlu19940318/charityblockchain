package com.charity.charityserver.Pojo.User;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

public class CardResp {
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "慈善总额")
    private long money;

    @ApiModelProperty(value = "poeid")
    private String poeid;

    @ApiModelProperty(value = "用户信息")
    private String info;

    @ApiModelProperty(value = "电话号码")
    private String tel;

    @ApiModelProperty(value = "慈善记录")
    private ArrayList<Record> recordlist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
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

    public ArrayList<Record> getRecordlist() {
        return recordlist;
    }

    public void setRecordlist(ArrayList<Record> recordlist) {
        this.recordlist = recordlist;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "CardResp{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", poeid='" + poeid + '\'' +
                ", info='" + info + '\'' +
                ", tel='" + tel + '\'' +
                ", recordlist=" + recordlist +
                '}';
    }
}
