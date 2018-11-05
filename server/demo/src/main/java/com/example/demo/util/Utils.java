package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.CouchDB;
import com.example.demo.pojo.User;

import java.util.ArrayList;

public class Utils {
    public static void pure(CouchDB couchDB, User user){
        couchDB.setInfo(user.getInfo());
        couchDB.setMoney(user.getMoney());
        couchDB.setName(user.getDname());
        couchDB.setState(user.getState());
        couchDB.setType(user.getDtype());
        couchDB.setCharity(JSON.parseObject(user.getCharity(), ArrayList.class));
    }

//    public static void main(String[] args) {
//        WalletUtils.queryPOE(WalletUtils.init(), "did:axn:eaae8375-a7b1-4de6-9057-1eccf26a380f");
//    }
}
