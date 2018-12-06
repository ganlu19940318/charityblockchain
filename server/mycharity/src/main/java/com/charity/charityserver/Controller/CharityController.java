package com.charity.charityserver.Controller;

import com.charity.charityserver.Pojo.Charity.CreateReq;
import com.charity.charityserver.Pojo.ResponseResult;
import com.charity.charityserver.Service.CharityService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/charity")
public class CharityController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CharityService charityService;

    @ApiOperation(value="创建慈善物品", notes="创建慈善物品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseResult create(@RequestBody CreateReq createReq){

        log.info("创建慈善物品被调用:"+createReq.toString());

        ResponseResult responseResult = charityService.doCreate(createReq);

        log.info("创建慈善物品调用结束:"+responseResult.toString());

        return responseResult;
    }

}
