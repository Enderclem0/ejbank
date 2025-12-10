package com.ejbank.ejbs.user;

import com.ejbank.entities.User;
import com.ejbank.payloads.UserDTO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class UserServiceEjb implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<UserDTO> getUserById(int userId) {
        var user = em.find(User.class, userId);
        if (user == null) {
            return Optional.empty();
        }
        var userPayload = new UserDTO(user.getFirstname(), user.getLastname());
        return Optional.of(userPayload);
    }
}
