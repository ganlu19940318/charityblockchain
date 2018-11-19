package com.example.demo.entity;

import java.util.Objects;

public class AddOk {
    // poeid
    private String address;
    private long money;
    private String itemname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddOk)) return false;
        AddOk addOk = (AddOk) o;
        return getMoney() == addOk.getMoney() &&
                Objects.equals(getAddress(), addOk.getAddress()) &&
                Objects.equals(getItemname(), addOk.getItemname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getMoney(), getItemname());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
