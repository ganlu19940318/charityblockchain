package com.charity.charityserver.Pojo.Farm;

public class MergeResp {
    private String dead;

    public String getDead() {
        return dead;
    }

    public void setDead(String dead) {
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "MergeResp{" +
                "dead='" + dead + '\'' +
                '}';
    }
}
