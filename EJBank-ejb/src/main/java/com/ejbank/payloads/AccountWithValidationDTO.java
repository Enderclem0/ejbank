package com.ejbank.payloads;

import java.math.BigDecimal;

public class AccountWithValidationDTO extends AccountDTO{
    private final int validation;

    public AccountWithValidationDTO(String id, String type, BigDecimal amount, int validation) {
        super(id, type, amount);
        this.validation = validation;
    }

    public int getValidation() {
        return validation;
    }

}
