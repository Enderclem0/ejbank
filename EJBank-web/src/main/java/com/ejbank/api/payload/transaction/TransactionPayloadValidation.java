package com.ejbank.api.payload.transaction;

public class TransactionPayloadValidation extends TransactionPayloadSubmission{
    private Exception error;

    public TransactionPayloadValidation(String message, Boolean result, Exception error) {
        super(message, result);
        this.error = error;
    }
}
