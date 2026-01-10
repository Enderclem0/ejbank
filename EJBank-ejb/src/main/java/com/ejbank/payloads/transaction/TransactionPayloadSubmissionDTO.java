package com.ejbank.payloads.transaction;

public class TransactionPayloadSubmissionDTO {
    private Boolean result;
    private String message;
    public TransactionPayloadSubmissionDTO(String message, Boolean result) {
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
