package com.charity.charityserver.Pojo.Charity;

public class CreateResp {
    private String pk64;
    private String walletid;
    private String poeid;

    public String getPk64() {
        return pk64;
    }

    public void setPk64(String pk64) {
        this.pk64 = pk64;
    }

    public String getWalletid() {
        return walletid;
    }

    public void setWalletid(String walletid) {
        this.walletid = walletid;
    }

    public String getPoeid() {
        return poeid;
    }

    public void setPoeid(String poeid) {
        this.poeid = poeid;
    }

    @Override
    public String toString() {
        return "CreateResp{" +
                "pk64='" + pk64 + '\'' +
                ", walletid='" + walletid + '\'' +
                ", poeid='" + poeid + '\'' +
                '}';
    }
}
