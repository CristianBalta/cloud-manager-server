package com.cristianbalta.cloudmanagerserver.service;

import com.cristianbalta.cloudmanagerserver.entity.User;
import com.cristianbalta.cloudmanagerserver.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginAndRegistrationService {

    private final UsersRepository usersRepository;

    public LoginAndRegistrationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean checkIfUserExists(String userEmail, String userPassword) {
        User user = usersRepository.findByUserEmail(userEmail);
        if (user != null) {
            return user.getUserPassword().equals(userPassword);
        } else return false;
    }
}
