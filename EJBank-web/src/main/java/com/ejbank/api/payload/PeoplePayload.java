package com.ejbank.api.payload;

import java.io.Serializable;

public class PeoplePayload implements Serializable {
    
    private String firstname;
    private String lastname;
    private Integer age;

    public PeoplePayload() {
    }

    public PeoplePayload(String firstname, String lastname, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
