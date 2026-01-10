package com.ejbank.payloads.transaction;

public class TransactionValidationPayloadDTO {
    private Long transactionId;
    private Boolean approve;
    private Long author;

    public TransactionValidationPayloadDTO(Long author, Boolean approve, Long transactionId) {
        this.author = author;
        this.approve = approve;
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }
}
