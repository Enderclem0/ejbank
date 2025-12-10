package com.ejbank.ejbs.account;

import com.ejbank.entities.Account;
import com.ejbank.entities.Advisor;
import com.ejbank.entities.Customer;
import com.ejbank.entities.User;
import com.ejbank.payloads.AccountDTO;
import com.ejbank.payloads.AccountDetailedDTO;
import com.ejbank.payloads.AccountWithOwnerNameDTO;
import com.ejbank.payloads.AccountWithValidationDTO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AccountServiceEjb implements AccountService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AccountDTO> getAccountsByUserId(int userId) {
        var user = em.find(User.class, userId);

        if (user == null) {
            throw new IllegalArgumentException("Utilisateur introuvable");
        }

        // Vérification du rôle
        if (user instanceof Advisor) {
            throw new SecurityException("Un conseiller ne peut pas consulter ses comptes via cette route");
        }

        var customer = (Customer) user;

        // Mapping
        return customer.getAccounts().stream().map(acc -> new AccountDTO(acc.getId().toString(), acc.getAccountType().getName(), acc.getBalance())).collect(Collectors.toList());
    }

    @Override
    public List<AccountWithOwnerNameDTO> getAllAccounts(int userId) {
        var user = em.find(User.class, userId);
        if (user == null) throw new IllegalArgumentException("Utilisateur introuvable");

        var result = new ArrayList<AccountWithOwnerNameDTO>();

        if (user instanceof Customer customer) {
            var ownerName = customer.getFirstname() + " " + customer.getLastname();

            for (Account acc : customer.getAccounts()) {
                result.add(new AccountWithOwnerNameDTO(acc.getId().toString(), acc.getAccountType().getName(), acc.getBalance(), ownerName));
            }

        } else if (user instanceof Advisor) {
            var accounts = em.createQuery("SELECT a FROM Account a JOIN FETCH a.customer c WHERE c.advisor.id = :advisorId", Account.class).setParameter("advisorId", userId).getResultList();

            for (Account acc : accounts) {
                String ownerName = acc.getCustomer().getFirstname() + " " + acc.getCustomer().getLastname();
                result.add(new AccountWithOwnerNameDTO(acc.getId().toString(), acc.getAccountType().getName(), acc.getBalance(), ownerName));
            }
        }

        return result;
    }

    @Override
    public List<AccountWithValidationDTO> getAttachedAccounts(int advisorId) {
        /*
        User user = em.find(User.class, advisorId);

        // Vérification du rôle : Interdit aux Clients
        if (user instanceof Customer) {
            throw new SecurityException("Un client n'a pas de comptes rattachés");
        }

        Advisor advisor = (Advisor) user;
        List<AccountWithValidationDTO> result = new ArrayList<>();

        for (Customer customer : advisor.getCustomers()) {
            String ownerName = customer.getFirstname() + " " + customer.getLastname();

            for (Account acc : customer.getAccounts()) {
                // TODO: Calculer le nombre de transactions en attente pour ce compte.
                // Il faudrait faire une query sur l'entité Transaction where applied=false
                int validationCount = 0;

                result.add(new AccountWithValidationDTO(
                        acc.getId().toString(),
                        acc.getAccountType().getName(),
                        acc.getBalance(),
                        ownerName,
                        validationCount
                ));
            }
        }
        return result;
         */
        return null; //TODO: After Transaction add this
    }

    @Override
    public AccountDetailedDTO getAccountDetailed(int accountId, int userId) {
        var account = em.find(Account.class, accountId);
        if (account == null) throw new IllegalArgumentException("Compte introuvable");

        var user = em.find(User.class, userId);
        if (user == null) throw new IllegalArgumentException("Utilisateur introuvable");

        var owner = account.getCustomer();
        var advisor = owner.getAdvisor();

        //Validation sécurité
        boolean isOwner = owner.getId() == userId;
        boolean isAccountAdvisor = (advisor != null) && (user instanceof Advisor adv) && ( adv.getId() == advisor.getId() );
        if (!isOwner && !isAccountAdvisor) {
            throw new SecurityException("Vous n'avez pas accès aux détails de ce compte.");
        }
        var rate = account.getAccountType().getRate();
        var balance = account.getBalance();
        // TODO interest: Get all transactions over the last year, for each 15 day
        //  calculate the backward amount and calculate the 15% interest then sum and count the total amount
        var interest = BigDecimal.ZERO;
        var advisorName = (advisor != null)
                ? advisor.getFirstname() + " " + advisor.getLastname()
                : "Aucun Conseiller";

        // Construction du DTO
        return new AccountDetailedDTO(owner.getFirstname() + " " + owner.getLastname(), advisorName, rate, interest, balance);
    }
}
