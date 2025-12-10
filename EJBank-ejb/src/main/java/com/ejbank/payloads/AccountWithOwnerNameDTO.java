package com.ejbank.payloads;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountWithOwnerNameDTO extends AccountDTO {
    private final String ownerName;
    public AccountWithOwnerNameDTO(String id, String type, BigDecimal amount, String ownerName) {
        super(id, type, amount);
        Objects.requireNonNull(ownerName);
        this.ownerName = ownerName;
    }

    public String getFirstnameLastName() {
        return ownerName;
    }
}
