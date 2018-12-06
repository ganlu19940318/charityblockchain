package com.charity.charityserver.Controller;

import com.charity.charityserver.Pojo.Farm.*;
import com.charity.charityserver.Pojo.ResponseResult;
import com.charity.charityserver.Service.FarmService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/farm")
public class FarmController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FarmService farmService;

    @ApiOperation(value="查看所有土地状态", notes="查看所有土地状态")
    @RequestMapping(value = "/land", method = RequestMethod.POST)
    public ResponseResult land(@RequestBody LandReq landReq){

        log.info("查看所有土地状态被调用:"+landReq.toString());

        ResponseResult responseResult = farmService.doLand(landReq);

        log.info("查看所有土地状态调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="查看背包所有物品", notes="查看背包所有物品")
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseResult item(@RequestBody ItemReq itemReq){

        log.info("查看背包所有物品被调用:"+itemReq.toString());

        ResponseResult responseResult = farmService.doItem(itemReq);

        log.info("查看背包所有物品调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="在土地上使用物品(种子,化肥)", notes="在土地上使用物品(种子,化肥)")
    @RequestMapping(value = "/useitem", method = RequestMethod.POST)
    public ResponseResult useitem(@RequestBody UseitemReq useitemReq){

        log.info("在土地上使用物品被调用:"+useitemReq.toString());

        ResponseResult responseResult = farmService.doUseitem(useitemReq);

        log.info("在土地上使用物品调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="收获作物", notes="收获作物")
    @RequestMapping(value = "/harvest", method = RequestMethod.POST)
    public ResponseResult harvest(@RequestBody HarvestReq harvestReq){

        log.info("收获作物被调用:"+harvestReq.toString());

        ResponseResult responseResult = farmService.doHarvest(harvestReq);

        log.info("收获作物调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="日常任务,可以获取普通种子或者化肥", notes="日常任务,可以获取普通种子或者化肥")
    @RequestMapping(value = "/seed", method = RequestMethod.POST)
    public ResponseResult seed(@RequestBody SeedReq seedReq){

        log.info("日常任务被调用:"+seedReq.toString());

        ResponseResult responseResult = farmService.doSeed(seedReq);

        log.info("日常任务调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="稀有种子领取", notes="稀有种子领取")
    @RequestMapping(value = "/rare", method = RequestMethod.POST)
    public ResponseResult rare(@RequestBody RareReq rareReq){

        log.info("稀有种子领取被调用:"+rareReq.toString());

        ResponseResult responseResult = farmService.doRare(rareReq);

        log.info("稀有种子领取调用结束:"+responseResult.toString());

        return responseResult;
    }


    @ApiOperation(value="稀有种子交易", notes="稀有种子交易")
    @RequestMapping(value = "/trade", method = RequestMethod.POST)
    public ResponseResult trade(@RequestBody TradeReq tradeReq){

        log.info("稀有种子交易被调用:"+tradeReq.toString());

        ResponseResult responseResult = farmService.doTrade(tradeReq);

        log.info("稀有种子交易调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="果实加工", notes="果实加工")
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    public ResponseResult merge(@RequestBody MergeReq mergeReq){

        log.info("果实加工被调用:"+mergeReq.toString());

        ResponseResult responseResult = farmService.doMerge(mergeReq);

        log.info("果实加工调用结束:"+responseResult.toString());

        return responseResult;
    }
}
