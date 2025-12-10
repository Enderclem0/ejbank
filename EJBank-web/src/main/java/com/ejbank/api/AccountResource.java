package com.ejbank.api;

import com.ejbank.api.payload.AccountDetailPayload;
import com.ejbank.api.payload.AccountListPayload;
import com.ejbank.ejbs.account.AccountService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/") // Root path, because we use both account and accounts
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    @EJB
    private AccountService accountService;

    @GET
    @Path("/accounts/{user_id}")
    public AccountListPayload<?> getMyAccounts(@PathParam("user_id") int userId) {
        try {
            return new AccountListPayload<>(accountService.getAccountsByUserId(userId));
        } catch (Exception e) {
            return new AccountListPayload<>(e.getMessage());
        }
    }

    @GET
    @Path("/accounts/attached/{user_id}")
    public AccountListPayload<?> getAttachedAccounts(@PathParam("user_id") int userId) {
        try {
            return new AccountListPayload<>(accountService.getAttachedAccounts(userId));
        } catch (Exception e) {
            return new AccountListPayload<>(e.getMessage());
        }
    }

    @GET
    @Path("/accounts/all/{user_id}")
    public AccountListPayload<?> getAllAccounts(@PathParam("user_id") int userId) {
        try {
            return new AccountListPayload<>(accountService.getAllAccounts(userId));
        } catch (Exception e) {
            return new AccountListPayload<>(e.getMessage());
        }
    }

    @GET
    @Path("/account/{account_id}/{user_id}")
    public AccountDetailPayload getAccountDetails(@PathParam("account_id") int accountId,
                                                  @PathParam("user_id") int userId) {
        try {
            var dto = accountService.getAccountDetailed(accountId, userId);
            return new AccountDetailPayload(
                    dto.getOwner(),
                    dto.getAdvisor(),
                    dto.getRate(),
                    dto.getInterest(),
                    dto.getBalance()
            );
        } catch (Exception e) {
            return new AccountDetailPayload(e.getMessage());
        }
    }
}