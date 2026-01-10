package com.ejbank.api.payload.transaction;

public class TransactionNotificationPayload {
    private int count;
    public TransactionNotificationPayload(int count) { this.count = count; }
    // Getter et Setter indispensables
    public int getCount() { return count; }
}
