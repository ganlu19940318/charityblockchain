package com.example.demo.service;

import com.example.demo.entity.*;

public interface BlockchainService {
    JsonResult doRegister(RegisterReq registerReq);
    JsonResult doLogin(LoginReq loginReq);
    JsonResult doCreate(CreateReq createReq);
    JsonResult doQuery();
    JsonResult doConfirm(ConfirmReq confirmReq);
    JsonResult doCard(CardReq cardReq);
    JsonResult doRank();
    JsonResult doTrade(TradeReq tradeReq);
}
