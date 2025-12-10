package com.ejbank.ejbs.user;

import com.ejbank.payloads.UserDTO;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface UserService {
    Optional<UserDTO> getUserById(int userId);
}
