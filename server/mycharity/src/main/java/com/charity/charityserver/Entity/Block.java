package com.charity.charityserver.Entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

// 用户信息表,这里存用户的链上信息
@Entity
public class Block {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.INSERT)
    @Id
    private Integer id;
    private String pk64;
    private String walletid;
    private String username;
    // 用户自己的poeid
    private String poeid;

    // 用户获得的慈善物品列表
    private ArrayList<String> poeidList;
    // 用户的总捐款额
    private long money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPoeid() {
        return poeid;
    }

    public void setPoeid(String poeid) {
        this.poeid = poeid;
    }

    public ArrayList<String> getPoeidList() {
        return poeidList;
    }

    public void setPoeidList(ArrayList<String> poeidList) {
        this.poeidList = poeidList;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
