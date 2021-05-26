package com.cristianbalta.cloudmanagerserver.service;

import com.cristianbalta.cloudmanagerserver.entity.User;
import com.cristianbalta.cloudmanagerserver.entity.Worker;
import com.cristianbalta.cloudmanagerserver.repository.UsersRepository;
import com.cristianbalta.cloudmanagerserver.repository.WorkersRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsersRepository usersRepository;
    private final WorkersRepository workersRepository;

    public LoginService(UsersRepository usersRepository, WorkersRepository workersRepository) {
        this.usersRepository = usersRepository;
        this.workersRepository = workersRepository;
    }

    public User createUser(User user) {
        return usersRepository.save(user);
    }

    public Worker createWorker(Worker worker) {
        return workersRepository.save(worker);
    }
}
