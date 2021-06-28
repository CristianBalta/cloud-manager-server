package com.cristianbalta.cloudmanagerserver.service;

import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import com.cristianbalta.cloudmanagerserver.entity.User;
import com.cristianbalta.cloudmanagerserver.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository userRepository) {
        this.usersRepository = userRepository;
    }

    public void createUser(UserDto userDto) throws Exception {
        User user = usersRepository.findByUserEmail(userDto.getUserEmail());
        if (user != null) {
            throw new Exception("User already exists!");
        } else {
            user = new User();
            user.setUserEmail(userDto.getUserEmail());
            user.setUserPassword(userDto.getUserPassword());
            usersRepository.save(user);
        }
    }

    public UserDto getUserByEmail(String userEmail) {
        User user = usersRepository.findByUserEmail(userEmail);
        UserDto userDto = new UserDto();
        userDto.setUserEmail(userEmail);
        userDto.setUserPassword(user.getUserPassword());
        return userDto;
    }
}
