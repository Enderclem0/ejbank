package com.ejbank.payloads.transaction;

import java.math.BigDecimal;

public class TransactionBasicPayloadDTO {
    private Long source;
    private Long destination;
    private BigDecimal amount;
    private Long author;

    public TransactionBasicPayloadDTO(Long source, Long destination, BigDecimal amount, Long author) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.author = author;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getDestination() {
        return destination;
    }

    public void setDestination(Long destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

}
