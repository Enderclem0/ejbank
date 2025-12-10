package com.ejbank.payloads;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountDetailedDTO {

    private final String owner; //Prénom + nom
    private final String advisor; //Prénom + nom
    private final BigDecimal rate; // interest rate
    private final BigDecimal interest; // computed interest
    private final BigDecimal balance;

    public AccountDetailedDTO(String owner, String advisor, BigDecimal rate, BigDecimal interest, BigDecimal balance) {
        Objects.requireNonNull(owner);
        Objects.requireNonNull(advisor);
        Objects.requireNonNull(rate);
        Objects.requireNonNull(interest);
        Objects.requireNonNull(balance);
        this.owner = owner;
        this.advisor = advisor;
        this.rate = rate;
        this.interest = interest;
        this.balance = balance;
    }

    public String getOwner() { return owner; }
    public String getAdvisor() { return advisor; }
    public BigDecimal getRate() { return rate; }
    public BigDecimal getInterest() { return interest; }
    public BigDecimal getBalance() { return balance; }
}
