package com.charity.charityserver.Service;

import com.charity.charityserver.Pojo.Charity.CreateReq;
import com.charity.charityserver.Pojo.ResponseResult;

public interface CharityService {
    ResponseResult doCreate(CreateReq createReq);
}
