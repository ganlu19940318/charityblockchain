package com.charity.charityserver.Pojo.Rank;

import io.swagger.annotations.ApiModelProperty;

public class UserReq {

    @ApiModelProperty(value = "1代表企业用户排行榜,2代表个人用户排行榜", required = true)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CardReq{" +
                "type='" + type + '\'' +
                '}';
    }
}
