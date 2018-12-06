package com.charity.charityserver.Entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CharityItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.INSERT)
    @Id
    private Integer id;

    private String walletid;
    private String pk64;
    private String poeid;

    // 慈善物品名称
    private String name;
    // 慈善物品描述
    private String info;
    // 慈善物品价值
    private long money;
    // 慈善物品所有者(的poeid)
    private String owner;
    // 慈善物品的状态,0代表无人认领,1代表已经被认领
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
