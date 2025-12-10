package com.ejbank.payloads;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountDTO {
    private final String id;
    private final String type;
    private final BigDecimal amount;

    public  AccountDTO(String id, String type, BigDecimal amount) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}