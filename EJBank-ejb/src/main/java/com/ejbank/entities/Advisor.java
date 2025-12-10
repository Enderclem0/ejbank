package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejbank_advisor")
@DiscriminatorValue("advisor")
@PrimaryKeyJoinColumn(name = "id")
public class Advisor extends User {

    @OneToMany(mappedBy = "advisor", fetch = FetchType.LAZY)
    private List<Customer> customers;

    public Advisor() {}

    public List<Customer> getCustomers() {
        return customers;
    }
}