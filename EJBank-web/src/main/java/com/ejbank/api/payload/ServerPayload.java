package com.ejbank.api.payload;

import java.io.Serializable;

public class ServerPayload implements Serializable {

    private final boolean result;

    public ServerPayload(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
