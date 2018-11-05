package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.entity.Enty;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Enty enty1 = new Enty();
        enty1.setAddress("1");
        enty1.setMoney(1);
        Enty enty2 = new Enty();
        enty2.setMoney(2);
        enty2.setAddress("2");
        ArrayList<Enty> arrayList = new ArrayList<>();
        arrayList.add(enty1);
        arrayList.add(enty2);
        String string = JSONArray.toJSONString(arrayList);
        System.out.println(string);

        ArrayList<Enty> arrayList1 = (ArrayList<Enty>)JSONArray.parseArray(string, Enty.class);
        for(Enty enty : arrayList1){
            System.out.println(enty.getMoney());
            System.out.println(enty.getAddress());
        }
    }
}
