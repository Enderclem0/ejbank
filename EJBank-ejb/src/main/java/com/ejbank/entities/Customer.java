package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejbank_customer")
@DiscriminatorValue("customer")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Customer() {
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}