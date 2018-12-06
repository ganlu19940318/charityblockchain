package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class TradeReq {
    @ApiModelProperty(value = "token", required = true)
    private String token;
    @ApiModelProperty(value = "种子poeid,也就是物品的poeid", required = true)
    private String seedpoeid;
    @ApiModelProperty(value = "对方用户的poeid", required = true)
    private String topoeid;
    @ApiModelProperty(value = "用户的poeid", required = true)
    private String mypoeid;
    @ApiModelProperty(value = "用户的walletid", required = true)
    private String mywalletid;
    @ApiModelProperty(value = "用户的pk64", required = true)
    private String mypk64;

    public String getSeedpoeid() {
        return seedpoeid;
    }

    public void setSeedpoeid(String seedpoeid) {
        this.seedpoeid = seedpoeid;
    }

    public String getTopoeid() {
        return topoeid;
    }

    public void setTopoeid(String topoeid) {
        this.topoeid = topoeid;
    }

    public String getMypoeid() {
        return mypoeid;
    }

    public void setMypoeid(String mypoeid) {
        this.mypoeid = mypoeid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMywalletid() {
        return mywalletid;
    }

    public void setMywalletid(String mywalletid) {
        this.mywalletid = mywalletid;
    }

    public String getMypk64() {
        return mypk64;
    }

    public void setMypk64(String mypk64) {
        this.mypk64 = mypk64;
    }

    @Override
    public String toString() {
        return "TradeReq{" +
                "token='" + token + '\'' +
                ", seedpoeid='" + seedpoeid + '\'' +
                ", topoeid='" + topoeid + '\'' +
                ", mypoeid='" + mypoeid + '\'' +
                ", mywalletid='" + mywalletid + '\'' +
                ", mypk64='" + mypk64 + '\'' +
                '}';
    }
}
