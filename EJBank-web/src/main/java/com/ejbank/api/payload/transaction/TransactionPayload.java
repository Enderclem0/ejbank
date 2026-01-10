package com.ejbank.api.payload.transaction;

import com.ejbank.payloads.transaction.TransactionSubmissionBasicPayloadDTO;

import java.math.BigDecimal;
import java.sql.Date;

public class TransactionPayload extends TransactionSubmissionBasicPayloadDTO {

    public enum State{
        APPLYED,
        TO_APPROVE,
        WAITING_APPROVE
    }
    private Integer id;
    private Date date;
    private Long source;
    private Long destination;
    private Long destinationUser;
    private BigDecimal amount;
    private Long author;
    private String comment;
    private State state;
    public TransactionPayload(Integer id, Date date, Long source, Long destination, Long destinationUser, BigDecimal amount, Long author, String comment, State state) {
        super(source, destination, amount, comment, author);
        this.id = id;
        this.date = date;
        this.destinationUser = destinationUser;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Long getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(Long destinationUser) {
        this.destinationUser = destinationUser;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
