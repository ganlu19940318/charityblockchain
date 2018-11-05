package com.example.demo.model.wallet;

public class CreatePOEResponse {
    private String id;
    private String created;
    private String[] transaction_ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
