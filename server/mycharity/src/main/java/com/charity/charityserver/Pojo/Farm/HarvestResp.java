package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class HarvestResp {
    @ApiModelProperty(value = "收获的果实id")
    private String itemid;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    @Override
    public String toString() {
        return "HarvestResp{" +
                "itemid='" + itemid + '\'' +
                '}';
    }
}
