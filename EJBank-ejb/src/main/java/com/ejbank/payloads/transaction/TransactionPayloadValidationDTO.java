package com.ejbank.payloads.transaction;

public class TransactionPayloadValidationDTO extends TransactionPayloadSubmissionDTO{
    private Exception error;

    public TransactionPayloadValidationDTO(String message, Boolean result, Exception error) {
        super(message, result);
        this.error = error;
    }
}
