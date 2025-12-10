package com.ejbank.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ejbank_account_type")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private BigDecimal rate;

    @Column
    private int overdraft;

    public AccountType() {}

    public String getName(){
        return name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public int  getOverdraft() {
        return overdraft;
    }
}