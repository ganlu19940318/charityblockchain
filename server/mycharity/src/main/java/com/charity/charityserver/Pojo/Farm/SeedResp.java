package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class SeedResp {
    @ApiModelProperty(value = "获取的化肥或者种子id")
    private String itemid;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    @Override
    public String toString() {
        return "SeedResp{" +
                "itemid='" + itemid + '\'' +
                '}';
    }
}
