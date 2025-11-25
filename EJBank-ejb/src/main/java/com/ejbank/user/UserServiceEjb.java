package com.ejbank.user;

import com.ejbank.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserServiceEjb implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getUserById(int userId) {
        return em.find(User.class, userId);
    }
}
