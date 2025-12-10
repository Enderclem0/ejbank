package com.ejbank.api.payload.transaction;

public class TransactionPayloadSubmission {
    private Boolean result;
    private String message;

    public TransactionPayloadSubmission(String message, Boolean result) {
        this.message = message;
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }
    public void setResult(Boolean result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
