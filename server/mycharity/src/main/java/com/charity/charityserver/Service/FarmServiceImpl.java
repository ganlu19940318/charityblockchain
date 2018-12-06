package com.charity.charityserver.Service;

import com.charity.charityserver.Dao.*;
import com.charity.charityserver.Data.ItemInfoDetails;
import com.charity.charityserver.Entity.*;
import com.charity.charityserver.Pojo.CodeMsg;
import com.charity.charityserver.Pojo.Farm.*;
import com.charity.charityserver.Pojo.ResponseResult;
import com.charity.charityserver.Utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class FarmServiceImpl implements FarmService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MappingTableRepository mappingTableRepository;

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CharityItemRepository charityItemRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Override
    public ResponseResult doLand(LandReq landReq) {
        // 将token转换为username,转换失败就返回重新登录
        if(landReq.getToken() == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        String username = stringRedisTemplate.opsForValue().get(landReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        // 根据用户名查找所有田地
        List<Farm> farms =  farmRepository.findFarmsByUsername(username);
        logger.info("OK:根据用户名查找所有田地");

        // 生成前端需要的数据格式
        List<LandResp> landRespList = new ArrayList<>();
        for(Farm farm : farms){
            LandResp landResp = new LandResp();
            landResp.setNumber(farm.getNumber());
            landResp.setTag(farm.getStatus());
            String status = farm.getStatus();
            if("1".equals(status)){
                long time2 = System.currentTimeMillis();
                long time1 = farm.getTime().getTime();
                long deta = time1 - time2;
                landResp.setMmsec(deta);
                landResp.setItemid(farm.getItemid());
            }
            landRespList.add(landResp);
        }
        logger.info("OK:生成前端需要的数据格式");

        // 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        responseResult.setData(landRespList);
        logger.info("OK:返回结果");

        return responseResult;
    }

    @Override
    public ResponseResult doItem(ItemReq itemReq) {

        // 将token转换为username,转换失败就返回重新登录
        if(itemReq.getToken() == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        String username = stringRedisTemplate.opsForValue().get(itemReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        // 查看所有物品
        List<Item> itemList = itemRepository.findItemsByUsernameAndStatus(username, "0");
        logger.info("OK:查看所有物品");

        // 生成前端需要的数据格式
        Map<String, Integer> map = new HashMap<>();
        for(Item item : itemList){
            if(map.get(item.getItemid()) == null){
                map.put(item.getItemid(), 1);
            }else {
                int value = (int)map.get(item.getItemid());
                value++;
                map.put(item.getItemid(), value);
            }
        }
        List<ItemResp> itemRespList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            ItemResp itemResp = new ItemResp();
            itemResp.setItemid(entry.getKey());
            itemResp.setNumber(entry.getValue());
            ItemInfo itemInfo = itemInfoRepository.findItemInfoByItemid(entry.getKey());
            itemResp.setItemname(itemInfo.getItemname());
            itemResp.setType(itemInfo.getItemtype());
            itemRespList.add(itemResp);
        }
        logger.info("OK:生成前端需要的数据格式");

        // 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        responseResult.setData(itemRespList);
        logger.info("OK:返回结果");

        return responseResult;
    }

    @Override
    public ResponseResult doUseitem(UseitemReq useitemReq) {

        // 将token转换为username,转换失败就返回重新登录
        if(useitemReq.getToken() == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        String username = stringRedisTemplate.opsForValue().get(useitemReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        // 编号是否在1-12
        int num = useitemReq.getNumber();
        if(num < 1 || num > 12){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.farm_code);
            responseResult.setMsg(CodeMsg.farm_msg);
            return responseResult;
        }
        logger.info("OK:编号是否在1-12");

        // 背包是否有该物品
        String itemid = useitemReq.getItemid();
        List<Item> items = itemRepository.findItemsByUsernameAndItemidAndStatus(username, itemid, "0");
        if(items == null || items.size() == 0){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.item_code);
            responseResult.setMsg(CodeMsg.item_msg);
            return responseResult;
        }
        Item item = items.get(0);
        logger.info("OK:背包有该物品");

        // 土地是否有作物
        Farm farm = farmRepository.findFarmByUsernameAndNumber(username, useitemReq.getNumber());
        if("1".equals(farm.getStatus())){
            // 有作物
            logger.info("有作物");
            ItemInfo itemInfo = itemInfoRepository.findItemInfoByItemid(itemid);
            String type = itemInfo.getItemtype();
            if(type.equals("3")){
                // 加化肥
                // 土地时间要改
                long time = 10 * 60 * 1000;
                long time2 = farm.getTime().getTime() - time;
                farm.setTime(new Date(time2));
                farmRepository.save(farm);
                // 物品使用状态也要改
                item.setStatus("1");
                itemRepository.save(item);
            }else {
                ResponseResult responseResult = new ResponseResult();
                responseResult.setCode(CodeMsg.itemuse_code);
                responseResult.setMsg(CodeMsg.itemuse_msg);
                return responseResult;
            }
        }else {
            // 无作物
            logger.info("无作物");
            ItemInfo itemInfo = itemInfoRepository.findItemInfoByItemid(itemid);
            String type = itemInfo.getItemtype();
            if(type.equals("2")){
                // 种种子
                // 土地是否有种子,种子编号,计划成熟时间要改
                farm.setStatus("1");
                farm.setItemid(itemid);
                long time = 30 * 60 * 1000;
                long time2 = time + System.currentTimeMillis();
                farm.setTime(new Date(time2));
                farmRepository.save(farm);
                // 物品使用状态也要改
                item.setStatus("1");
                itemRepository.save(item);
            }else {
                ResponseResult responseResult = new ResponseResult();
                responseResult.setCode(CodeMsg.itemuse_code);
                responseResult.setMsg(CodeMsg.itemuse_msg);
                return responseResult;
            }
        }
        logger.info("OK:土地是否有作物");

        // 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        logger.info("OK:返回结果");

        return responseResult;
    }

    @Override
    public ResponseResult doHarvest(HarvestReq harvestReq) {

        // 将token转换为username,转换失败就返回重新登录
        if(harvestReq.getToken() == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        String username = stringRedisTemplate.opsForValue().get(harvestReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        // 编号是否在1-12
        int num = harvestReq.getNumber();
        if(num < 1 || num > 12){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.farm_code);
            responseResult.setMsg(CodeMsg.farm_msg);
            return responseResult;
        }
        logger.info("OK:编号是否在1-12");

        // 拿到土地
        Farm farm = farmRepository.findFarmByUsernameAndNumber(username, harvestReq.getNumber());
        logger.info("OK:拿到土地");

        // 返回值
        HarvestResp harvestResp = new HarvestResp();

        // 土地有种子吗?
        if("1".equals(farm.getStatus())){
            // 种子成熟了吗?
            long time = farm.getTime().getTime();
            long time2 = System.currentTimeMillis() - time;
            if(time2 >= 0){
                // 用户获得果实
                MappingTable mappingTable = mappingTableRepository.findMappingTableByFromItemid(farm.getItemid());
                harvestResp.setItemid(mappingTable.getToItemid());
                Item item = new Item();
                item.setItemid(mappingTable.getToItemid());
                item.setStatus("0");
                item.setUsername(username);
                itemRepository.save(item);
                // 土地状态要改
                farm.setItemid(null);
                farm.setTime(null);
                farm.setStatus("0");
                farmRepository.save(farm);
            }else {
                ResponseResult responseResult = new ResponseResult();
                responseResult.setCode(CodeMsg.farmwait_code);
                responseResult.setMsg(CodeMsg.farmwait_msg);
                return responseResult;
            }
        }else {
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.farmno_code);
            responseResult.setMsg(CodeMsg.farmno_msg);
            return responseResult;
        }
        logger.info("OK:土地");

        // 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        responseResult.setData(harvestResp);
        logger.info("OK:返回结果");

        return responseResult;
    }

    @Override
    public ResponseResult doSeed(SeedReq seedReq) {
        // 将token转换为username,转换失败就返回重新登录
        if(seedReq.getToken() == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        String username = stringRedisTemplate.opsForValue().get(seedReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        // 当日种子领取是否达到上限
        String value = stringRedisTemplate.opsForValue().get(username+seedReq.getMethod());
        if(value != null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.task_code);
            responseResult.setMsg(CodeMsg.task_msg);
            return responseResult;
        }
        logger.info("OK:种子没达到上限");

        // 领取种子
        // Redis
        stringRedisTemplate.opsForValue().set(username+seedReq.getMethod(), "1", Utils.getRemainSecondsOneDay(new Date()), TimeUnit.SECONDS);
        // 用户item
        Item item = new Item();
        item.setUsername(username);
        item.setStatus("0");
        if("1".equals(seedReq.getMethod())){
            item.setItemid(ItemInfoDetails.itemid_seed1);
        }else {
            item.setItemid(ItemInfoDetails.itemid_huafei);
        }
        itemRepository.save(item);
        logger.info("OK:领取种子");

        // 返回成功
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        SeedResp seedResp = new SeedResp();
        seedResp.setItemid(ItemInfoDetails.itemid_seed1);
        responseResult.setData(seedResp);
        logger.info("OK:返回结果");

        return responseResult;
    }

    @Override
    public ResponseResult doRare(RareReq rareReq) {
        // 将token转换为username,转换失败就返回重新登录
        if(rareReq.getToken() == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        String username = stringRedisTemplate.opsForValue().get(rareReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        // 种子公私钥对验证
        CharityItem charityItem = charityItemRepository.findCharityItemByPoeid(rareReq.getSeedpoeid());
        if(charityItem == null || !charityItem.getWalletid().equals(rareReq.getSeedwalletid()) || !charityItem.getPk64().equals(rareReq.getSeedpk64())){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.not_code);
            responseResult.setMsg(CodeMsg.not_msg);
            return responseResult;
        }
        logger.info("OK:种子公私钥对验证pass");

        // 种子给用户
        charityItem.setOwner(rareReq.getUserpoeid());
        charityItem.setState("1");
        charityItemRepository.save(charityItem);
        logger.info("OK:种子给用户");

        // 用户链上信息在库中记录修改
        Block block = blockRepository.findBlockByUsername(username);
        ArrayList<String> arrayList = block.getPoeidList();
        arrayList.add(rareReq.getSeedpoeid());
        block.setPoeidList(arrayList);
        block.setMoney(block.getMoney()+charityItem.getMoney());
        logger.info("OK:用户链上信息在库中记录修改");

        // 用户获取稀有种子
        Item item = new Item();
        item.setItemid(ItemInfoDetails.itemid_seed2);
        item.setStatus("0");
        item.setUsername(username);
        itemRepository.save(item);
        logger.info("OK:用户获取稀有种子");

        // 返回成功
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        RareResp rareResp = new RareResp();
        rareResp.setItemid(item.getItemid());
        responseResult.setData(rareResp);
        logger.info("OK:返回结果");

        return responseResult;
    }

    @Override
    public ResponseResult doTrade(TradeReq tradeReq) {

        // 将token转换为username,转换失败就返回重新登录
        if(tradeReq.getToken() == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        String username = stringRedisTemplate.opsForValue().get(tradeReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        // 当前用户有没有稀有种子
        CharityItem charityItem = charityItemRepository.findCharityItemByPoeid(tradeReq.getSeedpoeid());
        if(charityItem == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.item_code);
            responseResult.setMsg(CodeMsg.item_msg);
            return responseResult;
        }
        if(!tradeReq.getMypoeid().equals(charityItem.getOwner())){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.item_code);
            responseResult.setMsg(CodeMsg.item_msg);
            return responseResult;
        }
        Block block = blockRepository.findBlockByPoeid(tradeReq.getMypoeid());
        if(!(block.getPk64().equals(tradeReq.getMypk64()) && block.getWalletid().equals(tradeReq.getMywalletid()))){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.not_code);
            responseResult.setMsg(CodeMsg.not_msg);
            return responseResult;
        }
        List<Item> itemList = itemRepository.findItemsByUsernameAndItemidAndStatus(username, ItemInfoDetails.itemid_seed2, "0");
        if(itemList == null || itemList.size() == 0){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.item_code);
            responseResult.setMsg(CodeMsg.item_msg);
            return responseResult;
        }
        logger.info("OK:当前用户有稀有种子");

        // block修改
        ArrayList<String> arrayList = block.getPoeidList();
        arrayList.remove(tradeReq.getSeedpoeid());
        block.setPoeidList(arrayList);
        block.setMoney(block.getMoney()-charityItem.getMoney());
        blockRepository.save(block);
        Block blockto = blockRepository.findBlockByPoeid(tradeReq.getTopoeid());
        ArrayList<String> arrayListto = blockto.getPoeidList();
        arrayListto.add(tradeReq.getSeedpoeid());
        blockto.setPoeidList(arrayListto);
        blockto.setMoney(blockto.getMoney()+charityItem.getMoney());
        blockRepository.save(blockto);
        logger.info("OK:block修改");

        // charityitem修改
        charityItem.setOwner(tradeReq.getTopoeid());
        charityItemRepository.save(charityItem);
        logger.info("OK:charityitem修改");

        // item修改
        Item item = itemList.get(0);
        item.setUsername(blockto.getUsername());
        itemRepository.save(item);
        logger.info("OK:item修改");

        // 返回成功
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        logger.info("OK:返回结果");

        return responseResult;
    }

    @Override
    public ResponseResult doMerge(MergeReq mergeReq) {
        // 将token转换为username,转换失败就返回重新登录
        if(mergeReq.getToken() == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        String username = stringRedisTemplate.opsForValue().get(mergeReq.getToken());
        if(username == null){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.relogin_code);
            responseResult.setMsg(CodeMsg.relogin_msg);
            return responseResult;
        }
        logger.info("OK:将token转换为username");

        List<Item> itemList = itemRepository.findItemsByUsernameAndItemidAndStatus(username, ItemInfoDetails.itemid_food1, "0");
        if(itemList.size() < 10){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(CodeMsg.not_code);
            responseResult.setMsg(CodeMsg.not_msg);
            return responseResult;
        }
        itemList = itemList.subList(0, 10);
        for(Item item : itemList){
            item.setStatus("1");
            itemRepository.save(item);
        }

        // 返回成功
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        MergeResp mergeResp = new MergeResp();
        mergeResp.setDead(Utils.getUUID());
        responseResult.setData(mergeResp);
        logger.info("OK:返回结果");

        return responseResult;
    }

}
