package com.cristianbalta.cloudmanagerserver.service;

import com.cristianbalta.cloudmanagerserver.dto.UserDto;
import com.cristianbalta.cloudmanagerserver.entity.User;
import com.cristianbalta.cloudmanagerserver.repository.UsersRepository;
import com.cristianbalta.cloudmanagerserver.security.util.HashingUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final WorkersService workersService;
    private final Logger logger;


    public UsersService(UsersRepository userRepository, WorkersService workersService, Logger logger) {
        this.usersRepository = userRepository;
        this.workersService = workersService;
        this.logger = logger;
    }

    public void createUser(UserDto userDto) throws Exception {
        User user = usersRepository.findByUserEmail(userDto.getUserEmail());
        if (user != null) {
            throw new Exception("User already exists!");
        } else {
            String salt = HashingUtil.getNewSalt();
            user = new User();
            user.setUserEmail(userDto.getUserEmail());
            user.setUserPassword(HashingUtil.getEncryptedPassword(userDto.getUserPassword(), salt));
            user.setUserSalt(salt);
            usersRepository.save(user);
        }
        logger.info("Created user with userEmail: {}", userDto.getUserEmail());
    }

    public UserDto getUserByEmail(String userEmail) throws Exception {
        User user = usersRepository.findByUserEmail(userEmail);
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setUserEmail(user.getUserEmail());
            userDto.setWorkerIpSet(workersService.getWorkersByUserEmail(userEmail));
            return userDto;
        } else {
            throw new Exception("User does not exist!");
        }
    }

    public UserDto getUserCredentials(String userEmail) throws Exception {
        User user = usersRepository.findByUserEmail(userEmail);
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setUserEmail(user.getUserEmail());
            userDto.setUserPassword(user.getUserPassword());
            return userDto;
        } else {
            throw new Exception("User does not exist!");
        }
    }

    @Transactional
    public void saveUserWorkerBearer(String userEmail, String bearerToken) {
        usersRepository.saveNewBearer(bearerToken, userEmail);
    }

    public String getUserWorkerBearer(String userEmail) {
        User user = usersRepository.findByUserEmail(userEmail);
        return user.getUserWorkerBearerToken();
    }
}
