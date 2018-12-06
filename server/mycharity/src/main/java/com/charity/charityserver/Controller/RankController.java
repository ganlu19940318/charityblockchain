package com.charity.charityserver.Controller;

import com.charity.charityserver.Pojo.Rank.UserReq;
import com.charity.charityserver.Pojo.ResponseResult;
import com.charity.charityserver.Service.RankService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/rank")
public class RankController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RankService rankService;

    @ApiOperation(value="用户排行榜(企业或者个人)", notes="用户排行榜(企业或者个人)")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseResult user(@RequestBody UserReq userReq){

        log.info("用户排行榜被调用:"+userReq.toString());

        ResponseResult responseResult = rankService.doUser(userReq);

        log.info("用户排行榜调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="慈善项目排行", notes="慈善项目排行")
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseResult project(){

        log.info("慈善项目排行被调用");

        ResponseResult responseResult = rankService.doProject();

        log.info("慈善项目排行调用结束:"+responseResult.toString());

        return responseResult;
    }
}
