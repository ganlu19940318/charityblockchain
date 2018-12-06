package com.charity.charityserver.Service;

import com.charity.charityserver.Pojo.Rank.UserReq;
import com.charity.charityserver.Pojo.ResponseResult;

public interface RankService {
    ResponseResult doUser(UserReq userReq);
    ResponseResult doProject();
}
