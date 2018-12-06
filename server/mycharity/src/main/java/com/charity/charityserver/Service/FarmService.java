package com.charity.charityserver.Service;

import com.charity.charityserver.Pojo.Farm.*;
import com.charity.charityserver.Pojo.ResponseResult;

public interface FarmService {
    ResponseResult doLand(LandReq landReq);
    ResponseResult doItem(ItemReq itemReq);
    ResponseResult doUseitem(UseitemReq useitemReq);
    ResponseResult doHarvest(HarvestReq harvestReq);
    ResponseResult doSeed(SeedReq seedReq);
    ResponseResult doRare(RareReq rareReq);
    ResponseResult doTrade(TradeReq tradeReq);
    ResponseResult doMerge(MergeReq mergeReq);
}
