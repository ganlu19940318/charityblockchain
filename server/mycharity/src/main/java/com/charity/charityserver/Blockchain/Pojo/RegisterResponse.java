package com.charity.charityserver.Blockchain.Pojo;

public class RegisterResponse {
    private String ErrCode;
    private String ErrMessage;
    private String Payload;

    public String getErrCode() {
        return ErrCode;
    }

    public void setErrCode(String errCode) {
        ErrCode = errCode;
    }

    public String getErrMessage() {
        return ErrMessage;
    }

    public void setErrMessage(String errMessage) {
        ErrMessage = errMessage;
    }

    public String getPayload() {
        return Payload;
    }

    public void setPayload(String payload) {
        Payload = payload;
    }
}
