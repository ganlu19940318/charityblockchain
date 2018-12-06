package com.charity.charityserver.Service;

import com.charity.charityserver.Blockchain.WalletUtils;
import com.charity.charityserver.Dao.CharityItemRepository;
import com.charity.charityserver.Entity.CharityItem;
import com.charity.charityserver.Pojo.Charity.CreateReq;
import com.charity.charityserver.Pojo.Charity.CreateResp;
import com.charity.charityserver.Pojo.CodeMsg;
import com.charity.charityserver.Pojo.ResponseResult;
import com.charity.charityserver.Utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharityServiceImpl implements CharityService {

    @Autowired
    private CharityItemRepository charityItemRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseResult doCreate(CreateReq createReq) {

        // 入参校验
        if(createReq.getInfo() == null || createReq.getInfo().equals("") || createReq.getName() == null || createReq.getName().equals("") || createReq.getMoney() == 0){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setMsg(CodeMsg.param_msg);
            responseResult.setData(CodeMsg.param_code);
            return responseResult;
        }
        logger.info("OK:入参校验成功");

        // 去链上注册
        String[] strs = null;
        try {
            strs = WalletUtils.register(Utils.getChar(), Utils.getChar());
        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult responseResult = new ResponseResult();
            responseResult.setMsg(CodeMsg.create_msg);
            responseResult.setData(CodeMsg.create_code);
            return responseResult;
        }
        String walletid = strs[0];
        String pk64 = strs[1];
        logger.info("OK:链上注册成功");

        // 注册成功,创建慈善物品
        String poeid = WalletUtils.createPOE(walletid, createReq.getName(), pk64, createReq.getInfo());
        logger.info("OK:创建慈善物品");

        // 创建成功,数据入库
        CharityItem charityItem = new CharityItem();
        charityItem.setInfo(createReq.getInfo());
        charityItem.setName(createReq.getName());
        charityItem.setMoney(createReq.getMoney());
        charityItem.setState("0");
        charityItem.setPk64(pk64);
        charityItem.setPoeid(poeid);
        charityItem.setWalletid(walletid);
        charityItemRepository.save(charityItem);

        // 操作成功,返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        CreateResp createResp = new CreateResp();
        createResp.setPk64(pk64);
        createResp.setPoeid(poeid);
        createResp.setWalletid(walletid);
        responseResult.setData(createResp);
        logger.info("OK:返回结果");

        return responseResult;
    }
}
