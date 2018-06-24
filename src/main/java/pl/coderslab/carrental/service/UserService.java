package pl.coderslab.carrental.service;

import pl.coderslab.carrental.persistence.model.User;
import pl.coderslab.carrental.web.dto.UserDto;

public interface UserService {
    User findUserByEmail(String email);

    void createUserAccount(UserDto user);
}
