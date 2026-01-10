package com.ejbank.ejbs.transaction;


import com.ejbank.entities.Transaction;
import com.ejbank.payloads.transaction.TransactionPayloadSubmissionDTO;
import com.ejbank.payloads.transaction.TransactionPreviewPayloadDTO;
import com.ejbank.payloads.transaction.TransactionSubmissionBasicPayloadDTO;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.List;

@Local
public interface TransactionService {
    List<Transaction> getListForAccount(Long accountId, Integer offset);

    int getCountForAccount(Long accountId);

    boolean transactionValidationPayload(Long transactionId, Boolean approve, Long authorId);
    int countPendingTransactionsForUser(Long userId);
    TransactionPreviewPayloadDTO preview(Long sourceAccountId, BigDecimal amount);
    TransactionPayloadSubmissionDTO apply(TransactionSubmissionBasicPayloadDTO payload) throws Exception;
}
