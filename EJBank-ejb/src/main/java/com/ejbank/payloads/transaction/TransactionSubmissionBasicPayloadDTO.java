package com.ejbank.payloads.transaction;

import java.math.BigDecimal;

public class TransactionSubmissionBasicPayloadDTO extends  TransactionBasicPayloadDTO {
    private String comment;

    public TransactionSubmissionBasicPayloadDTO(Long source, Long destination, BigDecimal amount, String comment, Long author) {
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
