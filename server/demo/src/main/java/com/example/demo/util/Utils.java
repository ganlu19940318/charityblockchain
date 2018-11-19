package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.entity.CouchDB;
import com.example.demo.entity.Enty;
import com.example.demo.pojo.User;

import java.util.ArrayList;

public class Utils {
    public static void pure(CouchDB couchDB, User user){
        couchDB.setInfo(user.getInfo());
        couchDB.setMoney(user.getMoney());
        couchDB.setName(user.getDname());
        couchDB.setType(user.getDtype());
        couchDB.setCharity((ArrayList<Enty>)JSONArray.parseArray(user.getCharity(), Enty.class));
    }

//    public static void main(String[] args) {
//        ArrayList<Enty> arrayList = new ArrayList<>();
//        Enty enty = new Enty();
//        enty.setAddress("111");
//        enty.setMoney(111);
//        Enty enty2 = new Enty();
//        enty2.setAddress("222");
//        enty2.setMoney(222);
//        arrayList.add(enty);
//        arrayList.add(enty2);
//        String s = JSONArray.toJSONString(arrayList);
//        System.out.println(s);
//        ArrayList arrayList1 = (ArrayList<Enty>)JSONArray.parseArray(s, Enty.class);
//        System.out.println(arrayList1);
//    }
}
