package com.charity.charityserver.Pojo.Rank;

import io.swagger.annotations.ApiModelProperty;

public class ProjectResp {
    @ApiModelProperty(value = "项目名称")
    private String name;
    @ApiModelProperty(value = "捐赠金额")
    private long money;
    @ApiModelProperty(value = "项目地址,poeid")
    private String poeid;
    @ApiModelProperty(value = "项目状态,,0代表无人认领,1代表已经被认领")
    private String status;
    @ApiModelProperty(value = "所有者地址,poeid")
    private String owner;
    @ApiModelProperty(value = "项目信息")
    private String info;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ProjectResp{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", poeid='" + poeid + '\'' +
                ", status='" + status + '\'' +
                ", owner='" + owner + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
