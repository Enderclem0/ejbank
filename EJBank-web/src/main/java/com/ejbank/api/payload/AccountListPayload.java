package com.ejbank.api.payload;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AccountListPayload<T> implements Serializable {
    private final List<T> accounts;
    private final String error;

    public AccountListPayload(List<T> accounts) {
        Objects.requireNonNull(accounts);
        this.accounts = List.copyOf(accounts);
        this.error = null;
    }

    public AccountListPayload(String error) {
        this.accounts = null;
        this.error = error;
    }

    public List<T> getAccounts() { return accounts; }
    public String getError() { return error; }
}
