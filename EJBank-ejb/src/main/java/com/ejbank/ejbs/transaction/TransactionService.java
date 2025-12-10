package com.ejbank.ejbs.transaction;


import com.ejbank.entities.Transaction;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TransactionService {
    List<Transaction> getListForAccount(Long accountId, Integer offset);
}
