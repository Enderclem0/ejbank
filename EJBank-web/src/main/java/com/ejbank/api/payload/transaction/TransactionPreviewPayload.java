package com.ejbank.api.payload.transaction;

import java.math.BigDecimal;

public class TransactionPreviewPayload extends TransactionPayloadValidation {
    private BigDecimal before;
    private BigDecimal after;

    public TransactionPreviewPayload(Boolean result, BigDecimal before, BigDecimal after, String message, Exception error) {
        super(message, result, error);
        this.before = before;
        this.after = after;
    }

    public BigDecimal getBefore() {
        return before;
    }

    public void setBefore(BigDecimal before) {
        this.before = before;
    }

    public BigDecimal getAfter() {
        return after;
    }

    public void setAfter(BigDecimal after) {
        this.after = after;
    }
}
