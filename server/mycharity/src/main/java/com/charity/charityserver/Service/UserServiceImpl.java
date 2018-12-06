package com.charity.charityserver.Service;

import com.charity.charityserver.Blockchain.WalletUtils;
import com.charity.charityserver.Dao.BlockRepository;
import com.charity.charityserver.Dao.CharityItemRepository;
import com.charity.charityserver.Dao.FarmRepository;
import com.charity.charityserver.Dao.UserRepository;
import com.charity.charityserver.Entity.Block;
import com.charity.charityserver.Entity.CharityItem;
import com.charity.charityserver.Entity.Farm;
import com.charity.charityserver.Entity.User;
import com.charity.charityserver.Pojo.CodeMsg;
import com.charity.charityserver.Pojo.User.*;
import com.charity.charityserver.Pojo.ResponseResult;
import com.charity.charityserver.Utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private CharityItemRepository charityItemRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private FarmRepository farmRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseResult doRegister(RegisterReq registerReq) {

        // 参数校验
        if(StringUtils.isEmpty(registerReq.getUsername()) || StringUtils.isEmpty(registerReq.getInfo())){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.param_code);
            responseResult.setMsg(CodeMsg.param_msg);
            return responseResult;
        }

        // 用户名是否被注册过
        User user = userRepository.findUserByUsername(registerReq.getUsername());
        if(user != null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.register_code);
            responseResult.setMsg(CodeMsg.register_msg);
            return responseResult;
        }
        logger.info("OK:用户没有被注册过");

        // 去链上为用户注册一个账号
        String[] registerresp = new String[0];
        try {
            registerresp = WalletUtils.register(registerReq.getUsername(), registerReq.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.chainregister_code);
            responseResult.setMsg(CodeMsg.chainregister_msg);
            return responseResult;
        }
        String walletid = registerresp[0];
        String pk64 = registerresp[1];
        logger.info("OK:链上注册成功");

        // 为用户在链上创建资产
        String poeid = WalletUtils.createPOE(walletid, registerReq.getUsername(), pk64, registerReq.getInfo());
        logger.info("OK:为用户在链上创建资产");

        // 链上注册成功, 用户信息数据入库
        user = new User();
        user.setName(registerReq.getName());
        user.setIdCard(registerReq.getIdCard());
        user.setInfo(registerReq.getInfo());
        user.setPassword(registerReq.getPassword());
        user.setTel(registerReq.getTel());
        user.setType(registerReq.getType());
        user.setUsername(registerReq.getUsername());
        userRepository.save(user);
        logger.info("OK:用户信息数据存入数据库");

        // 链上注册成功, 链上信息数据入库
        Block block = new Block();
        block.setPoeidList(new ArrayList<>());
        block.setPk64(pk64);
        block.setWalletid(walletid);
        block.setUsername(registerReq.getUsername());
        block.setPoeid(poeid);
        blockRepository.save(block);
        logger.info("OK:链上信息数据存入数据库");

        // 代理用户开通农场
        for(int i = 1; i <= 12; i++){
            Farm farm = new Farm();
            farm.setNumber(i);
            farm.setUsername(registerReq.getUsername());
            farm.setStatus("0");
            farmRepository.save(farm);
        }
        logger.info("OK:代理用户开通农场");

        // 代理向Redis登录
        String token = Utils.getUUID();
        stringRedisTemplate.opsForValue().set(token, registerReq.getUsername());
        logger.info("OK:向Redis登录");

        // 注册结束, 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        RegisterResp registerResp = new RegisterResp();
        registerResp.setPk64(pk64);
        registerResp.setToken(token);
        registerResp.setPoeid(poeid);
        registerResp.setWalletid(walletid);
        responseResult.setData(registerResp);
        logger.info("OK:注册结束");

        return responseResult;
    }

    @Override
    public ResponseResult doLogin(LoginReq loginReq) {
        // 校验用户名
        User user = userRepository.findUserByUsername(loginReq.getUsername());
        if(user == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.login_code);
            responseResult.setMsg(CodeMsg.login_msg);
            return responseResult;
        }
        logger.info("OK:校验用户名");

        // 校验密码
        if(!user.getPassword().equals(loginReq.getPassword())){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.password_code);
            responseResult.setMsg(CodeMsg.password_msg);
            return responseResult;
        }
        logger.info("OK:校验密码");

        // 代理向Redis登录
        String token = Utils.getUUID();
        stringRedisTemplate.opsForValue().set(token, loginReq.getUsername());
        logger.info("OK:向Redis登录");

        // 登录结束, 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(token);
        loginResp.setType(user.getType());
        responseResult.setData(loginResp);
        logger.info("OK:登录结束");

        return responseResult;
    }

    @Override
    public ResponseResult doCard(CardReq cardReq) {

        // 将token转换为username,转换失败就返回重新登录
        String username = stringRedisTemplate.opsForValue().get(cardReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        // 返回值
        CardResp cardResp = new CardResp();

        // 信息查询
        User user = userRepository.findUserByUsername(username);
        Block block = blockRepository.findBlockByUsername(username);
        cardResp.setName(user.getName());
        cardResp.setInfo(user.getInfo());
        cardResp.setTel(user.getTel());
        cardResp.setMoney(block.getMoney());
        cardResp.setPoeid(block.getPoeid());
        ArrayList<Record> recordArrayList = new ArrayList<>();
        for(String itempoeid : block.getPoeidList()){
            Record record = new Record();
            CharityItem charityItem = charityItemRepository.findCharityItemByPoeid(itempoeid);
            record.setMoney(charityItem.getMoney());
            record.setName(charityItem.getName());
            record.setPoeid(itempoeid);
            recordArrayList.add(record);
        }
        cardResp.setRecordlist(recordArrayList);
        logger.info("OK:信息查询");

        // 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        responseResult.setData(cardResp);
        logger.info("OK:返回结果");

        return responseResult;
    }

}
