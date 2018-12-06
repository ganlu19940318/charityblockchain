package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class ItemResp {

    @ApiModelProperty(value = "物品id")
    private String itemid;
    @ApiModelProperty(value = "物品名称")
    private String itemname;
    @ApiModelProperty(value = "物品数量")
    private int number;
    @ApiModelProperty(value = "物品类型,1表示果实,2表示种子,3表示化肥")
    private String type;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ItemResp{" +
                "itemid='" + itemid + '\'' +
                ", itemname='" + itemname + '\'' +
                ", number=" + number +
                ", type='" + type + '\'' +
                '}';
    }
}

