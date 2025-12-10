package com.ejbank.payloads;

import java.util.Objects;

public class UserDTO {
    private final String firstname;
    private final String lastname;

    public UserDTO(String firstname, String lastname) {
        Objects.requireNonNull(firstname, "firstname must not be null");
        Objects.requireNonNull(lastname, "lastname must not be null");
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

}
