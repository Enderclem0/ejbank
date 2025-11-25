package com.ejbank.user;

import javax.ejb.Local;

@Local
public interface UserService {
    String getUserById(String userId);
}
