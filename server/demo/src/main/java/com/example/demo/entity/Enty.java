package com.example.demo.entity;

import java.util.Objects;

public class Enty {
    // poeid
    private String address;
    private long money;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enty)) return false;
        Enty enty = (Enty) o;
        return getMoney() == enty.getMoney() &&
                Objects.equals(getAddress(), enty.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getMoney());
    }

    public Enty() {
    }

    public Enty(String address, long money) {
        this.address = address;
        this.money = money;
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
}
