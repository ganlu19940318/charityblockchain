package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.example.demo.service.BlockchainService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
@Controller
public class BlockchainController {

    private static final Logger logger = LoggerFactory.getLogger(BlockchainController.class);

    @Autowired
    private BlockchainService blockchainService;

    @ApiOperation(value="用户注册", notes="用户注册")
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(RegisterReq registerReq) throws Exception {

        logger.info("registerReq:"+registerReq);

        JsonResult jsonResult = blockchainService.doRegister(registerReq);

        return JSONObject.toJSONString(jsonResult);
    }

    @ApiOperation(value="用户登录", notes="用户登录")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(LoginReq loginReq) throws Exception {

        logger.info("loginReq.getUsername:"+loginReq.getUsername());
        logger.info("loginReq.getPassword:"+loginReq.getPassword());

        JsonResult jsonResult = blockchainService.doLogin(loginReq);

        return JSONObject.toJSONString(jsonResult);
    }

    @ApiOperation(value="创建慈善物品", notes="创建慈善物品")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(CreateReq createReq) throws Exception {

        logger.info("createReq:"+createReq);

        JsonResult jsonResult = blockchainService.doCreate(createReq);
        System.out.println("create:"+JSONObject.toJSONString(jsonResult));
        return JSONObject.toJSONString(jsonResult);
    }

    @ApiOperation(value="查看机构自己的所有物品", notes="查看机构自己的所有物品")
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String query(QueryReq queryReq) throws Exception {

        logger.info("query all");

        JsonResult jsonResult = blockchainService.doQuery(queryReq);

        return JSONObject.toJSONString(jsonResult);
    }

    @ApiOperation(value="确认慈善项目", notes="确认慈善项目")
    @ResponseBody
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(ConfirmReq confirmReq) throws Exception {

        logger.info("confirmReq:"+confirmReq);

        JsonResult jsonResult = blockchainService.doConfirm(confirmReq);

        return JSONObject.toJSONString(jsonResult);
    }

    @ApiOperation(value="查看慈善名片", notes="查看慈善名片")
    @ResponseBody
    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public String card(CardReq cardReq) throws Exception {

        logger.info("cardReq:"+cardReq);

        JsonResult jsonResult = blockchainService.doCard(cardReq);

        System.out.println("result:"+ JSONObject.toJSONString(jsonResult));
        return JSONObject.toJSONString(jsonResult);
    }

    @ApiOperation(value="查看慈善排行(用户)", notes="查看慈善排行(用户)")
    @ResponseBody
    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    public String rank() throws Exception {

        logger.info("do rank");

        JsonResult jsonResult = blockchainService.doRank();

        return JSONObject.toJSONString(jsonResult);
    }

    @ApiOperation(value="查看慈善排行(物品)", notes="查看慈善排行(物品)")
    @ResponseBody
    @RequestMapping(value = "/rankitem", method = RequestMethod.POST)
    public String rankitem() throws Exception {

        logger.info("do rankitem");

        JsonResult jsonResult = blockchainService.doRankItem();

        return JSONObject.toJSONString(jsonResult);
    }

    @ApiOperation(value="慈善记录交易", notes="慈善记录交易")
    @ResponseBody
    @RequestMapping(value = "/trade", method = RequestMethod.POST)
    public String trade(TradeReq tradeReq) throws Exception {

        logger.info("tradeReq:"+tradeReq);

        JsonResult jsonResult = blockchainService.doTrade(tradeReq);

        return JSONObject.toJSONString(jsonResult);
    }
}
