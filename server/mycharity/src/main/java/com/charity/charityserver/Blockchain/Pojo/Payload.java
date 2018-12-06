package com.charity.charityserver.Blockchain.Pojo;

public class Payload {
    private int code;
    private String message;
    private String id;
    private String endpoint;
    private KeyPair key_pair;
    private String created;
    private String[] transaction_ids;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public KeyPair getKey_pair() {
        return key_pair;
    }

    public void setKey_pair(KeyPair key_pair) {
        this.key_pair = key_pair;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String[] getTransaction_ids() {
        return transaction_ids;
    }

    public void setTransaction_ids(String[] transaction_ids) {
        this.transaction_ids = transaction_ids;
    }
}
