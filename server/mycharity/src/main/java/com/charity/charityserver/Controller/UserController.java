package com.charity.charityserver.Controller;

import com.charity.charityserver.Pojo.ResponseResult;
import com.charity.charityserver.Pojo.User.CardReq;
import com.charity.charityserver.Pojo.User.LoginReq;
import com.charity.charityserver.Pojo.User.RegisterReq;
import com.charity.charityserver.Service.UserService;
import com.charity.charityserver.Utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户注册", notes="用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult register(@RequestBody RegisterReq registerReq){


        log.info("用户注册被调用:"+registerReq.toString());

        ResponseResult responseResult = userService.doRegister(registerReq);

        log.info("用户注册调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="用户登录", notes="用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody LoginReq loginReq){

        log.info("用户登录被调用:"+loginReq.toString());

        ResponseResult responseResult = userService.doLogin(loginReq);

        log.info("用户登录调用结束:"+responseResult.toString());

        return responseResult;
    }

    @ApiOperation(value="查看慈善名片", notes="查看慈善名片")
    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public ResponseResult card(@RequestBody CardReq cardReq){

        log.info("查看慈善名片被调用:"+cardReq.toString());

        ResponseResult responseResult = userService.doCard(cardReq);

        log.info("查看慈善名片调用结束:"+responseResult.toString());

        return responseResult;

    }


    @ApiOperation(value="test", notes="test")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(){

        stringRedisTemplate.opsForValue().set("111","222",60, TimeUnit.SECONDS);
        int i = Utils.getRemainSecondsOneDay(new Date());
        System.out.println(i);
        return "test";
    }



}
