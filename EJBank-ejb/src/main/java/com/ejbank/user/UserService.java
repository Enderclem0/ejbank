package com.ejbank.user;

import com.ejbank.entities.User;

import javax.ejb.Local;

@Local
public interface UserService {
    User getUserById(int userId);
}
