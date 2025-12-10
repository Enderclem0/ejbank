package com.ejbank.ejbs.account;

import com.ejbank.payloads.AccountDTO;
import com.ejbank.payloads.AccountDetailedDTO;
import com.ejbank.payloads.AccountWithOwnerNameDTO;
import com.ejbank.payloads.AccountWithValidationDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountService {

    List<AccountDTO> getAccountsByUserId(int userId);

    List<AccountWithOwnerNameDTO> getAllAccounts(int userId);

    List<AccountWithValidationDTO> getAttachedAccounts(int advisorId);

    AccountDetailedDTO getAccountDetailed(int accountId, int userId);
}
