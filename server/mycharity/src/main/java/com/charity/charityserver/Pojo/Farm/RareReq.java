package com.charity.charityserver.Pojo.Farm;

import io.swagger.annotations.ApiModelProperty;

public class RareReq {
    @ApiModelProperty(value = "用户token", required = true)
    private String token;
    @ApiModelProperty(value = "用户poeid", required = true)
    private String userpoeid;
    @ApiModelProperty(value = "种子poeid", required = true)
    private String seedpoeid;
    @ApiModelProperty(value = "种子walletid", required = true)
    private String seedwalletid;
    @ApiModelProperty(value = "种子pk64", required = true)
    private String seedpk64;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserpoeid() {
        return userpoeid;
    }

    public void setUserpoeid(String userpoeid) {
        this.userpoeid = userpoeid;
    }

    public String getSeedpoeid() {
        return seedpoeid;
    }

    public void setSeedpoeid(String seedpoeid) {
        this.seedpoeid = seedpoeid;
    }

    public String getSeedwalletid() {
        return seedwalletid;
    }

    public void setSeedwalletid(String seedwalletid) {
        this.seedwalletid = seedwalletid;
    }

    public String getSeedpk64() {
        return seedpk64;
    }

    public void setSeedpk64(String seedpk64) {
        this.seedpk64 = seedpk64;
    }

    @Override
    public String toString() {
        return "RareReq{" +
                "token='" + token + '\'' +
                ", userpoeid='" + userpoeid + '\'' +
                ", seedpoeid='" + seedpoeid + '\'' +
                ", seedwalletid='" + seedwalletid + '\'' +
                ", seedpk64='" + seedpk64 + '\'' +
                '}';
    }
}
