package com.ejbank.api.payload.transaction;

import java.math.BigDecimal;

public class TransactionSubmissionBasicPayload extends  TransactionBasicPayload {
    private String comment;

    public TransactionSubmissionBasicPayload(Long source, Long destination, BigDecimal amount, String comment, Long author) {
        super(source, destination, amount, author);
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
