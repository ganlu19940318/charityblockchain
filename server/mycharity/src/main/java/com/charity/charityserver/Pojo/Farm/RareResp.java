package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class RareResp {
    @ApiModelProperty(value = "获取的稀有种子id")
    private String itemid;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    @Override
    public String toString() {
        return "RareResp{" +
                "itemid='" + itemid + '\'' +
                '}';
    }
}
