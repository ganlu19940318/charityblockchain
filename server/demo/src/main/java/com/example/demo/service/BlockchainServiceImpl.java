package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.arxanfintech.sdk.wallet.Wallet;
import com.example.demo.dao.ItemRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.*;
import com.example.demo.pojo.Item;
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

    @Autowired
    private ItemRepository itemRepository;

    private static final Logger logger = LoggerFactory.getLogger(BlockchainServiceImpl.class);

    @Override
    public JsonResult doRegister(RegisterReq registerReq) {
        if("1".equals(registerReq.getType())){
            return registerUser(registerReq);
        }else {
            return registerCharity(registerReq);
        }
    }
    private JsonResult registerUser(RegisterReq registerReq){
        Wallet wallet = WalletUtils.init();
        String access = registerReq.getUsername();
        String secret = registerReq.getPassword();
        String[] response = new String[0];
        try {
            response = WalletUtils.register(wallet, access, secret);
        } catch (Exception e) {
            JsonResult jsonResult = new JsonResult();
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        String walletid = response[0];
        String privatekeyBase64 = response[1];
        String info = registerReq.getInfo();
        String name = registerReq.getName();
        String type = registerReq.getType();
        String tel = registerReq.getTel();
        User user = new User();
        user.setTel(tel);
        user.setDtype(type);
        user.setDname(name);
        user.setInfo(info);
        user.setCharity(JSONArray.toJSONString(new ArrayList<Enty>()));
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
    private JsonResult registerCharity(RegisterReq registerReq){
        Wallet wallet = WalletUtils.init();
        String access = registerReq.getUsername();
        String secret = registerReq.getPassword();
        String[] response = new String[0];
        try {
            response = WalletUtils.register(wallet, access, secret);
        } catch (Exception e) {
            JsonResult jsonResult = new JsonResult();
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        String walletid = response[0];
        String privatekeyBase64 = response[1];
        String info = registerReq.getInfo();
        User user = new User();
        user.setDtype(registerReq.getType());
        user.setDname(registerReq.getName());
        user.setInfo(info);
        user.setCharity(JSONArray.toJSONString(new ArrayList<Enty>()));
        user.setMoney(0);
        user.setTel(registerReq.getTel());
        user.setAddress(walletid);
        user.setPk64(privatekeyBase64);
        user.setUsername(access);
        user.setPassword(secret);
        userRepository.save(user);
        RegisterResp registerResp = new RegisterResp();
        registerResp.setAddress(walletid);
        registerResp.setPk64(privatekeyBase64);
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
        loginResp.setType(user.getDtype());
        loginResp.setAddress(user.getAddress());
        loginResp.setPk64(user.getPk64());
        if("1".equals(user.getDtype())){
            loginResp.setPoeid(user.getPoeid());
        }
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
//        if(!createReq.getPassword().equals(user.getPassword())){
//            jsonResult.setResult_code(-1);
//            return jsonResult;
//        }
        System.out.println("point 3");
        if(!"0".equals(user.getDtype())){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        // verify pass

        // create
        // init
        Wallet wallet = WalletUtils.init();
        // create params
        String infos = JSON.toJSONString(new Enty());
        String poeid_item = WalletUtils.createPOE(wallet, user.getAddress(), createReq.getName(), user.getPk64(), infos);
        Item item = new Item();
        item.setAddress(user.getAddress());
        item.setInfo(createReq.getInfo());
        item.setMoney(createReq.getMoney());
        item.setName(createReq.getName());
        item.setPk64(user.getPk64());
        item.setPoeid(poeid_item);
        item.setState("0");
        itemRepository.save(item);

        ArrayList<Enty> arrayListtemp = (ArrayList<Enty>)JSONArray.parseArray(user.getCharity(), Enty.class);
        arrayListtemp.add(new Enty(poeid_item, createReq.getMoney()));
        user.setCharity(JSONArray.toJSONString(arrayListtemp));
        logger.info("charity:"+user.getCharity());
        CouchDB couchDB = new CouchDB();
        Utils.pure(couchDB, user);
        String info = JSON.toJSONString(couchDB);
        logger.info("info params:"+info);
        WalletUtils.updataPOE(wallet, user.getPoeid(), user.getAddress(), user.getPk64(), info);
        userRepository.save(user);
        System.out.println("point 4");
        // ok
        jsonResult.setResult_code(0);
        CreateResp createResp = new CreateResp();
        createResp.setPoeid(poeid_item);
        createResp.setAddress(item.getAddress());
        createResp.setPk64(item.getPk64());
        jsonResult.setResult(createResp);
        return jsonResult;
    }

    @Override
    public JsonResult doQuery(QueryReq queryReq) {
        JsonResult jsonResult = new JsonResult();

        List<Item> items = itemRepository.findItemsByAddress(queryReq.getAddress());
        if(items == null || items.size() == 0){
            jsonResult.setResult_code(0);
            List<QueryResp> queryResps = new ArrayList<>();
            jsonResult.setResult(queryResps);
            return jsonResult;
        }

        jsonResult.setResult_code(0);
        List<QueryResp> queryResps = new ArrayList<>();
        for(Item item : items){
            QueryResp queryResp = new QueryResp();
            queryResp.setState(item.getState());
            queryResp.setName(item.getName());
            queryResp.setMoney(item.getMoney());
            queryResp.setInfo(item.getInfo());
            queryResp.setAddress(item.getPoeid());
            queryResp.setDonateaddress(item.getOwner());
            queryResps.add(queryResp);
        }
        jsonResult.setResult(queryResps);
        return jsonResult;
    }

    @Override
    public JsonResult doConfirm(ConfirmReq confirmReq) {
        JsonResult jsonResult = new JsonResult();
        // query mysql
        List<Item> list = itemRepository.findItemsByPoeid(confirmReq.getPoeid());
        if(list == null || list.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        Item item = list.get(0);
        item.setState("1");
        item.setOwner(confirmReq.getUseraddress());
        itemRepository.save(item);
        // motify blockchain
        Wallet wallet = WalletUtils.init();
        String info = JSON.toJSONString(new Enty(confirmReq.getUseraddress(), item.getMoney()));
        WalletUtils.updataPOE(wallet, confirmReq.getPoeid(), confirmReq.getAddress(), confirmReq.getPk64(), info);

        // create params
        List<User> users = userRepository.findAllByPoeid(confirmReq.getUseraddress());
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user = users.get(0);
        ArrayList<Enty> arrayList = (ArrayList<Enty>)JSONArray.parseArray(user.getCharity(), Enty.class);
        arrayList.add(new Enty(confirmReq.getPoeid(), item.getMoney()));
        user.setCharity(JSON.toJSONString(arrayList));
        user.setMoney(user.getMoney() + item.getMoney());
        CouchDB couchDB = new CouchDB();
        Utils.pure(couchDB, user);
        userRepository.save(user);
        WalletUtils.updataPOE(wallet, user.getPoeid(), user.getAddress(), user.getPk64(), JSON.toJSONString(couchDB));
        // motify mysql-person
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
        ArrayList<Enty> arrayListHAHA = (ArrayList<Enty>)JSONArray.parseArray(user.getCharity(), Enty.class);
        arrayListHAHA.sort(new Comparator<Enty>() {
            @Override
            public int compare(Enty o1, Enty o2) {
                if(o1.getMoney() > o2.getMoney()){
                    return -1;
                }
                return 0;
            }
        });

        // add itemname to Charity
        ArrayList<AddOk> addOks = new ArrayList<>();
        for(Enty enty : arrayListHAHA){
            AddOk addOk = new AddOk();
            addOk.setAddress(enty.getAddress());
            addOk.setMoney(enty.getMoney());
            String itemname = itemRepository.findItemsByPoeid(enty.getAddress()).get(0).getName();
            addOk.setItemname(itemname);
            addOks.add(addOk);
        }
        addOks.sort(new Comparator<AddOk>() {
            @Override
            public int compare(AddOk o1, AddOk o2) {
                if(o1.getMoney() > o2.getMoney()){
                    return -1;
                }
                return 0;
            }
        });

//        List arrayListHAHA2 = arrayListHAHA.subList(0, Math.min(arrayListHAHA.size(), 5));
        List arrayListHAHA22 = addOks.subList(0, Math.min(addOks.size(), 5));
//        cardResp.setCharity(user.getCharity());
//        cardResp.setCharity(JSONArray.toJSONString(arrayListHAHA2));
        cardResp.setCharity(JSONArray.toJSONString(arrayListHAHA22));
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
        Wallet wallet = WalletUtils.init();
        JsonResult jsonResult = new JsonResult();
        // user
        // mysql
        List<Item> items = itemRepository.findItemsByPoeid(tradeReq.getCharitypoeid());
        if(items == null || items.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        Item item = items.get(0);
        List<User> users = userRepository.findAllByPoeid(tradeReq.getPoeid());
        if(users == null || users.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User user = users.get(0);
        ArrayList<Enty> arrayList = (ArrayList<Enty>)JSONArray.parseArray(user.getCharity(), Enty.class);
        Enty enty = new Enty(item.getPoeid(), item.getMoney());
        arrayList.remove(enty);
        user.setMoney(user.getMoney() - item.getMoney());
        user.setCharity(JSONArray.toJSONString(arrayList));

        CouchDB couchDB = new CouchDB();
        Utils.pure(couchDB, user);
        userRepository.save(user);
        WalletUtils.updataPOE(wallet, user.getPoeid(), user.getAddress(), user.getPk64(), JSON.toJSONString(couchDB));

        // touser
        // mysql
        List<User> tousers = userRepository.findAllByPoeid(tradeReq.getTopoeid());
        if(tousers == null || tousers.size() == 0){
            jsonResult.setResult_code(-1);
            return jsonResult;
        }
        User touser = tousers.get(0);
        touser.setMoney(touser.getMoney() + enty.getMoney());
        ArrayList<Enty> arrayList2 = (ArrayList<Enty>)JSONArray.parseArray(touser.getCharity(), Enty.class);
        arrayList2.add(enty);
        touser.setCharity(JSONArray.toJSONString(arrayList2));
        CouchDB couchDB2 = new CouchDB();
        Utils.pure(couchDB2, touser);
        userRepository.save(touser);
        WalletUtils.updataPOE(wallet, touser.getPoeid(), touser.getAddress(), touser.getPk64(), JSON.toJSONString(couchDB2));

        // block
        // charity
        // mysql
        item.setOwner(touser.getPoeid());
        itemRepository.save(item);
        // block
        String infos = JSON.toJSONString(enty);
        WalletUtils.updataPOE(wallet, item.getPoeid(), item.getAddress(), item.getPk64(), infos);
        jsonResult.setResult_code(0);
        return jsonResult;
    }

    @Override
    public JsonResult doRankItem() {
        JsonResult jsonResult = new JsonResult();

        Iterable<Item> items = itemRepository.findAll();

        jsonResult.setResult_code(0);
        List<QueryResp> queryResps = new ArrayList<>();
        for(Item item : items){
            QueryResp queryResp = new QueryResp();
            queryResp.setState(item.getState());
            queryResp.setName(item.getName());
            queryResp.setMoney(item.getMoney());
            queryResp.setInfo(item.getInfo());
            queryResp.setAddress(item.getPoeid());
            queryResp.setDonateaddress(item.getOwner());
            queryResps.add(queryResp);
        }
        jsonResult.setResult(queryResps);
        return jsonResult;
    }


}
