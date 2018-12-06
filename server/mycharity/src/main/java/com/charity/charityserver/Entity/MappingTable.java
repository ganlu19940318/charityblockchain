package com.charity.charityserver.Entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 映射表,种子和果实的映射关系,由管理人员录入
@Entity
public class MappingTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.INSERT)
    @Id
    private Integer id;

    // 种子id
    private String fromItemid;
    // 果实id
    private String toItemid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromItemid() {
        return fromItemid;
    }

    public void setFromItemid(String fromItemid) {
        this.fromItemid = fromItemid;
    }

    public String getToItemid() {
        return toItemid;
    }

    public void setToItemid(String toItemid) {
        this.toItemid = toItemid;
    }
}
