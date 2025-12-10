package com.ejbank.api.payload.transaction;

import java.util.List;

public class TransactionPayloadList {
    private final List<TransactionPayload> transactionPayloadList;
    private final Integer total;
    private final String error;

    public TransactionPayloadList(List<TransactionPayload> transactionPayloadList, Integer total, String error) {
        this.transactionPayloadList = transactionPayloadList;
        this.total = total;
        this.error = error;
    }

    public List<TransactionPayload> getTransactionPayloadList() {
        return transactionPayloadList;
    }
}
