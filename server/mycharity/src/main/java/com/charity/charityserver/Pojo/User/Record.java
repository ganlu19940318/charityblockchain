package com.charity.charityserver.Pojo.User;

public class Record {
    // 慈善名称
    private String name;
    // 慈善项目的poeid
    private String poeid;
    // 慈善金额
    private long money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoeid() {
        return poeid;
    }

    public void setPoeid(String poeid) {
        this.poeid = poeid;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", poeid='" + poeid + '\'' +
                ", money=" + money +
                '}';
    }
}
