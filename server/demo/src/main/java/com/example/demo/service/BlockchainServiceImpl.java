package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.arxanfintech.sdk.wallet.Wallet;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.*;
import com.example.demo.pojo.User;
import com.example.demo.util.Utils;
import com.example.demo.util.WalletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BlockchainServiceImpl implements BlockchainService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(BlockchainServiceImpl.class);

    @Override
    public JsonResult doRegister(RegisterReq registerReq) {
        Wallet wallet = WalletUtils.init();
        String access = registerReq.getUsername();
        String secret = registerReq.getPassword();
        String[] response = WalletUtils.register(wallet, access, secret);
        String walletid = response[0];
        String privatekeyBase64 = response[1];
        String info = "welcome";
        User user = new User();
        user.setDtype(registerReq.getType());
        user.setDname(registerReq.getName());
        user.setInfo(info);
        user.setState("-1");
        user.setCharity(JSONArray.toJSONString(new ArrayList<>()));
        user.setMoney(0);
        user.setAddress(walletid);
        user.setPk64(privatekeyBase64);
        user.setUsername(access);
        user.setPassword(secret);
        CouchDB couchDB = new CouchDB();
        Utils.pure(couchDB, user);
        String poeid = WalletUtils.createPOE(wallet, walletid, registerReq.getName(), privatekeyBase64, JSON.toJSONString(couchDB));
        user.setPoeid(poeid);
        userRepository.save(user);
        RegisterResp registerResp = new RegisterResp();
        registerResp.setAddress(walletid);
        registerResp.setPk64(privatekeyBase64);
        registerResp.setPoeid(poeid);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult_code(0);
        jsonResult.setResult(registerResp);
        return jsonResult;
    }

    @Override
    public JsonResult doLogin(LoginReq loginReq) {
        JsonResult jsonResult = new JsonResult();
        List<User> users = userRepository.findAllByUsername(loginReq.getUsername());
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user = users.get(0);
        if(!loginReq.getPassword().equals(user.getPassword())){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        LoginResp loginResp = new LoginResp();
        loginResp.setAddress(user.getAddress());
        loginResp.setPk64(user.getPk64());
        loginResp.setPoeid(user.getPoeid());
        jsonResult.setResult_code(0);
        jsonResult.setResult(loginResp);
        return jsonResult;
    }

    @Override
    public JsonResult doCreate(CreateReq createReq) {
        // verify
        JsonResult jsonResult = new JsonResult();
        List<User> users = userRepository.findAllByUsername(createReq.getUsername());
        System.out.println("point 1");
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        System.out.println("point 2");
        User user = users.get(0);
        if(!createReq.getPassword().equals(user.getPassword())){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        System.out.println("point 3");
        // verify pass

        // create
        // init
        Wallet wallet = WalletUtils.init();
        // create params
        user.setMoney(createReq.getMoney());
        user.setInfo(createReq.getInfo());
        user.setState("0");
        CouchDB couchDB = new CouchDB();
        Utils.pure(couchDB, user);
        String info = JSON.toJSONString(couchDB);
        logger.info("info params:"+info);
        WalletUtils.updataPOE(wallet, user.getPoeid(), user.getAddress(), user.getPk64(), info);
        // save to mysql
        userRepository.save(user);
        System.out.println("point 4");
        // ok
        jsonResult.setResult_code(0);
        return jsonResult;
    }

    @Override
    public JsonResult doQuery() {
        JsonResult jsonResult = new JsonResult();
        List<User> users = userRepository.findAllByDtypeAndStateIsNotContaining("0", "-1");
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(0);
            List<QueryResp> queryResps = new ArrayList<>();
            jsonResult.setResult(queryResps);
            return jsonResult;
        }
        jsonResult.setResult_code(0);
        List<QueryResp> queryResps = new ArrayList<>();
        for(User user : users){
            QueryResp queryResp = new QueryResp();
            queryResp.setAddress(user.getPoeid());
            queryResp.setInfo(user.getInfo());
            queryResp.setMoney(user.getMoney());
            queryResp.setName(user.getDname());
            queryResp.setState(user.getState());
            queryResp.setDonateaddress((ArrayList<Enty>)JSONArray.parseArray(user.getCharity(), Enty.class));
            queryResps.add(queryResp);
        }
        jsonResult.setResult(queryResps);
        return jsonResult;
    }

    // to be test
    @Override
    public JsonResult doConfirm(ConfirmReq confirmReq) {
        JsonResult jsonResult = new JsonResult();
        // query mysql
        List<User> users = userRepository.findAllByAddress(confirmReq.getAddress());
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user = users.get(0);
        // create params
        ArrayList<Enty> charity = (ArrayList<Enty>)JSONArray.parseArray(user.getCharity(), Enty.class);
        Enty enty = new Enty();
        enty.setAddress(confirmReq.getUseraddress());
        enty.setMoney(confirmReq.getMoney());
        charity.add(enty);
        user.setCharity(JSONArray.toJSONString(charity));
        CouchDB couchDB = new CouchDB();
        Utils.pure(couchDB, user);
        // motify mysql
        userRepository.save(user);
        // motify blockchain
        Wallet wallet = WalletUtils.init();
        WalletUtils.updataPOE(wallet, confirmReq.getPoeid(), confirmReq.getAddress(), confirmReq.getPk64(), JSON.toJSONString(couchDB));
        // query mysql-person
        List<User> users2 = userRepository.findAllByPoeid(confirmReq.getUseraddress());
        if(users2 == null || users2.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user2 = users2.get(0);
        // create params
        user2.setMoney(user2.getMoney()+confirmReq.getMoney());
        ArrayList<Enty> charity2 = (ArrayList<Enty>)JSONArray.parseArray(user2.getCharity(), Enty.class);
        Enty enty2 = new Enty();
        enty2.setAddress(confirmReq.getPoeid());
        enty2.setMoney(confirmReq.getMoney());
        charity2.add(enty2);
        user2.setCharity(JSONArray.toJSONString(charity2));
        CouchDB couchDB2 = new CouchDB();
        Utils.pure(couchDB2, user2);
        // motify mysql-person
        userRepository.save(user2);
        // motify blockchain-person
        Wallet wallet2 = WalletUtils.init();
        WalletUtils.updataPOE(wallet2, user2.getPoeid(), user2.getAddress(), user2.getPk64(), JSON.toJSONString(couchDB2));
        jsonResult.setResult_code(0);
        return jsonResult;
    }

    @Override
    public JsonResult doCard(CardReq cardReq) {
        JsonResult jsonResult = new JsonResult();
        List<User> users = userRepository.findAllByPoeid(cardReq.getAddress());
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user = users.get(0);
        CardResp cardResp = new CardResp();
        cardResp.setCharity(user.getCharity());
        cardResp.setInfo(user.getInfo());
        cardResp.setMoney(user.getMoney());
        cardResp.setName(user.getDname());
        cardResp.setPoeid(user.getPoeid());
        jsonResult.setResult(cardResp);
        return jsonResult;
    }

    @Override
    public JsonResult doRank() {
        JsonResult jsonResult = new JsonResult();
        List<User> users = userRepository.findAllByDtype("1");
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        RankResp rankResp = new RankResp();
        List<CardResp> list = new ArrayList<>();
        for(User user : users){
            CardResp cardResp = new CardResp();
            cardResp.setCharity(user.getCharity());
            cardResp.setInfo(user.getInfo());
            cardResp.setMoney(user.getMoney());
            cardResp.setName(user.getDname());
            cardResp.setPoeid(user.getPoeid());
            list.add(cardResp);
        }
        list.sort(new Comparator<CardResp>() {
            @Override
            public int compare(CardResp o1, CardResp o2) {
                if(o1.getMoney() < o2.getMoney()){
                    return 1;
                }
                return -1;
            }
        });
        rankResp.setCardResps(list);
        jsonResult.setResult(rankResp);
        return jsonResult;
    }

    // to be test
    @Override
    public JsonResult doTrade(TradeReq tradeReq) {
        JsonResult jsonResult = new JsonResult();
        // user
        // mysql
        List<User> users = userRepository.findAllByAddress(tradeReq.getAddress());
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user = users.get(0);
        ArrayList<Enty> userarray = (ArrayList<Enty>)JSONArray.parseArray(user.getCharity(), Enty.class);
        System.out.println("enty size:"+userarray.size());
        Enty temp = new Enty();
        for(Enty enty : userarray){
            if(enty.getAddress().equals(tradeReq.getCharitypoeid())){
                temp = enty;
                user.setMoney(user.getMoney()-enty.getMoney());
                userarray.remove(enty);
                break;
            }
        }
        user.setCharity(JSONArray.toJSONString(userarray));
        userRepository.save(user);
        // block
        Wallet wallet = WalletUtils.init();
        CouchDB usercouchDB = new CouchDB();
        Utils.pure(usercouchDB, user);
        String userinfo = JSON.toJSONString(usercouchDB);
        WalletUtils.updataPOE(wallet, tradeReq.getPoeid(), tradeReq.getAddress(), tradeReq.getPk64(), userinfo);
        // touser
        // mysql
        List<User> users2 = userRepository.findAllByPoeid(tradeReq.getTopoeid());
        if(users2 == null || users2.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user2 = users2.get(0);
        user2.setMoney(user2.getMoney()+temp.getMoney());
        ArrayList<Enty> arrays2 = (ArrayList<Enty>)JSONArray.parseArray(user2.getCharity(), Enty.class);
        arrays2.add(temp);
        user2.setCharity(JSONArray.toJSONString(arrays2));
        userRepository.save(user2);
        // block
        Wallet wallet2 = WalletUtils.init();
        CouchDB usercouchDB2 = new CouchDB();
        Utils.pure(usercouchDB2, user2);
        WalletUtils.updataPOE(wallet2, user2.getPoeid(), user2.getAddress(), user2.getPk64(), JSON.toJSONString(usercouchDB2));
        // charity
        // mysql
        List<User> users3 = userRepository.findAllByPoeid(tradeReq.getCharitypoeid());
        if(users3 == null || users3.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user3 = users3.get(0);
        ArrayList<Enty> arrayList3 = (ArrayList<Enty>)JSONArray.parseArray(user3.getCharity(), Enty.class);
        long m = 0;
        for(Enty enty : arrayList3){
            if(enty.getAddress().equals(tradeReq.getPoeid())){
                m = enty.getMoney();
                arrayList3.remove(enty);
                break;
            }
        }
        Enty entynew = new Enty();
        entynew.setMoney(m);
        entynew.setAddress(tradeReq.getTopoeid());
        arrayList3.add(entynew);
        user3.setCharity(JSONArray.toJSONString(arrayList3));
        userRepository.save(user3);
        // block
        CouchDB couchDB3 = new CouchDB();
        Utils.pure(couchDB3, user3);
        Wallet wallet3= WalletUtils.init();
        WalletUtils.updataPOE(wallet3, user3.getPoeid(), user3.getAddress(), user3.getPk64(), JSON.toJSONString(couchDB3));

        jsonResult.setResult_code(0);
        return jsonResult;
    }


}
