package com.charity.charityserver.Pojo.User;

import io.swagger.annotations.ApiModelProperty;

public class RegisterResp {
    @ApiModelProperty(value = "token,登录成功后的所有操作需要带上token,用于验证和身份标识")
    private String token;
    @ApiModelProperty(value = "钱包ID,用户妥善保管,用户唯一拥有")
    private String walletid;
    @ApiModelProperty(value = "pk64,用户妥善保管,用户唯一拥有")
    private String pk64;
    @ApiModelProperty(value = "poeid,用户妥善保管,用户唯一拥有")
    private String poeid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWalletid() {
        return walletid;
    }

    public void setWalletid(String walletid) {
        this.walletid = walletid;
    }

    public String getPk64() {
        return pk64;
    }

    public void setPk64(String pk64) {
        this.pk64 = pk64;
    }

    public String getPoeid() {
        return poeid;
    }

    public void setPoeid(String poeid) {
        this.poeid = poeid;
    }

    @Override
    public String toString() {
        return "RegisterResp{" +
                "token='" + token + '\'' +
                ", walletid='" + walletid + '\'' +
                ", pk64='" + pk64 + '\'' +
                ", poeid='" + poeid + '\'' +
                '}';
    }
}
