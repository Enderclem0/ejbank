package com.ejbank.api.payload;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountDetailPayload {
    private final String owner;
    private final String advisor;
    private final BigDecimal rate;
    private final BigDecimal interest;
    private final BigDecimal amount;
    private final String error;

    public AccountDetailPayload(String owner, String advisor,  BigDecimal rate, BigDecimal interest, BigDecimal amount) {
        Objects.requireNonNull(owner);
        Objects.requireNonNull(advisor);
        Objects.requireNonNull(rate);
        Objects.requireNonNull(interest);
        Objects.requireNonNull(amount);
        this.owner = owner;
        this.advisor = advisor;
        this.rate = rate;
        this.interest = interest;
        this.amount = amount;
        this.error = null;
    }

    public AccountDetailPayload(String error) {
        this.error = error;
        this.owner = null;
        this.rate = null;
        this.interest = null;
        this.amount = null;
        this.advisor = null;
    }

    // Getters
    public String getOwner() { return owner; }
    public String getAdvisor() { return advisor; }
    public java.math.BigDecimal getRate() { return rate; }
    public java.math.BigDecimal getInterest() { return interest; }
    public java.math.BigDecimal getAmount() { return amount; }
    public String getError() { return error; }
}