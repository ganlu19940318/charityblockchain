package com.charity.charityserver.Service;

import com.charity.charityserver.Pojo.ResponseResult;
import com.charity.charityserver.Pojo.User.CardReq;
import com.charity.charityserver.Pojo.User.LoginReq;
import com.charity.charityserver.Pojo.User.RegisterReq;

public interface UserService {
    ResponseResult doRegister(RegisterReq registerReq);
    ResponseResult doLogin(LoginReq loginReq);
    ResponseResult doCard(CardReq cardReq);
}
