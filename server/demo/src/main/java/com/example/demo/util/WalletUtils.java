package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arxanfintech.common.rest.Client;
import com.arxanfintech.sdk.wallet.Wallet;
import com.example.demo.model.wallet.CreatePOEResponse;
import com.example.demo.model.wallet.Payload;
import com.example.demo.model.wallet.QueryPOEResponse;
import com.example.demo.model.wallet.RegisterResponse;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class WalletUtils {
    public static Wallet init(){
        String address = "139.198.15.132:9143";
        String apiKey = "vs6YuzNTp1540982983";
        String certPath = "/home/ganlu/cryption/ecc/certs";
        String signParamsCreator = "did:axn:0d92c80d-8273-4a84-b858-c81234b4bd83";
        String signParamsNonce = "nonce";
        String signParamsPrivatekeyBase64 = "yDMVXaCDoxw7zvNAdTjXeUZXglCVWfOfsaLukMc7OR5XI4EHLlLtF3vHi1V3CL3u2bZiD2+ZkOJn5OdN9BfM7w==";
        String signParamsCreated = "1534723900";

        Client client = new Client(apiKey,certPath,signParamsCreator,signParamsCreated,signParamsNonce,signParamsPrivatekeyBase64,address); // enableCrypto default true if not set
        Wallet wallet = new Wallet(client);
        System.out.println("init ok!");
        return wallet;
    }

    public static String[] register(Wallet wallet, String access, String secret) throws Exception{
        String strheader = "{\"Bc-Invoke-Mode\":\"sync\"}";
        String strdata = "{\"access\":\"" + access +"\", \"secret\": \""+secret+"\", \"type\": \"Person\"}";
        JSONObject jsondata = JSON.parseObject(strdata);
        JSONObject jsonheader = JSON.parseObject(strheader);

        String response = null;
        response = wallet.register(jsonheader, jsondata).toString();
        System.out.println("response:"+response);
        RegisterResponse registerResponse = JSON.parseObject(response, RegisterResponse.class);
        Payload payload = JSON.parseObject(registerResponse.getPayload(), Payload.class);
        String id = payload.getId();
        String privatekeyBase64 = payload.getKey_pair().getPrivate_key();
        System.out.println("register ok!");
        System.out.println("wallet id:"+id);
        System.out.println("privatekeyBase64:"+privatekeyBase64);
        return new String[]{id, privatekeyBase64};
    }

    public static String createPOE(Wallet wallet, String id, String name, String privatekeyBase64, String info){
        String signToolPath = "/home/ganlu/src/github.com/arxanchain/sdk-go-common/crypto/tools/build/bin/sign-util";
        String strheader = "{\"Bc-Invoke-Mode\":\"sync\"}";
        JSONObject jsonheader = JSON.parseObject(strheader);

        BASE64Encoder encoder = new BASE64Encoder();
        String encodedText = null;
        try {
            encodedText = encoder.encode(info.getBytes("UTF-8"));
            encodedText = encodedText.replaceAll("[\\s*\t\n\r]", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String strdata = "{\"name\":\"" + name +"\", \"owner\": \""+id+"\", \"metadata\": \""+encodedText+"\", \"creator\": \""+id+"\"}";
        System.out.println("strdata:"+strdata);
        JSONObject payload = JSON.parseObject(strdata);
        String did = id;
        String created = "1534723900";
        String nonce = "nonce";
        String response = null;
        try {
            response = wallet.createPOE(jsonheader, payload, did, created, nonce, privatekeyBase64, signToolPath).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("response:"+response);
        RegisterResponse registerResponse = JSON.parseObject(response, RegisterResponse.class);
        CreatePOEResponse createPOEResponse = JSON.parseObject(registerResponse.getPayload(), CreatePOEResponse.class);
        String POEid = createPOEResponse.getId();
        System.out.println("POE id:"+POEid);
        return POEid;
    }

    public static String queryPOE(Wallet wallet, String id){
        String strheader = "{\"Bc-Invoke-Mode\":\"sync\"}";
        JSONObject jsonheader = JSON.parseObject(strheader);
        String response = null;
        try {
            response = wallet.queryPOE(jsonheader, id).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Decoder decoder = new BASE64Decoder();
        System.out.println("response:"+response);
        RegisterResponse registerResponse = JSON.parseObject(response, RegisterResponse.class);
        QueryPOEResponse queryPOEResponse = JSON.parseObject(registerResponse.getPayload(), QueryPOEResponse.class);
        String info = queryPOEResponse.getHash();
        System.out.println("info:"+info);
        String metadata = queryPOEResponse.getMetadata();
        String val = null;
        try {
            val = new String(decoder.decodeBuffer(metadata), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("metadata:"+val);
        return val;
    }

    public static void updataPOE(Wallet wallet, String POEid, String owner, String privateKeyBase64, String info){
        String strheader = "{\"Bc-Invoke-Mode\":\"sync\"}";
        JSONObject jsonheader = JSON.parseObject(strheader);
        BASE64Encoder encoder = new BASE64Encoder();
        String encodedText = null;
        try {
            encodedText = encoder.encode(info.getBytes("UTF-8"));
            encodedText = encodedText.replaceAll("[\\s*\t\n\r]", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String strdata = "{\"id\":\"" + POEid +"\", \"owner\": \""+owner+"\", \"metadata\":\""+encodedText+"\"}";
        JSONObject payload = JSON.parseObject(strdata);
        String creator = owner;
        String created = System.currentTimeMillis()/1000+"";
        String nonce = "nonce";
        String signToolPath = "/home/ganlu/src/github.com/arxanchain/sdk-go-common/crypto/tools/build/bin/sign-util";
        String response = null;
        try {
            response = wallet.updatePOE(jsonheader, payload, creator, created, nonce, privateKeyBase64, signToolPath).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("response:"+response);
    }
}
