package com.charity.charityserver.Pojo.Farm;


import io.swagger.annotations.ApiModelProperty;

public class LandResp {
    @ApiModelProperty(value = "农田编号")
    private int number;
    @ApiModelProperty(value = "是否有农作物, 0表示没有农作物, 1表示有农作物")
    private String tag;
    @ApiModelProperty(value = "剩余成熟的时间,精确到毫秒")
    private long mmsec;
    @ApiModelProperty(value = "作物编号")
    private String itemid;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getMmsec() {
        return mmsec;
    }

    public void setMmsec(long mmsec) {
        this.mmsec = mmsec;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    @Override
    public String toString() {
        return "LandResp{" +
                "number=" + number +
                ", tag='" + tag + '\'' +
                ", mmsec=" + mmsec +
                ", itemid='" + itemid + '\'' +
                '}';
    }
}
