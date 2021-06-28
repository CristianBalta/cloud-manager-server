package com.cristianbalta.cloudmanagerserver.dto;

import java.util.Set;

public class UserDto {

    private Long userId;

    private String userEmail;

    private String userPassword;

    private Set<WorkerDto> workerDtoSet;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<WorkerDto> getWorkerDtoSet() {
        return workerDtoSet;
    }

    public void setWorkerDtoSet(Set<WorkerDto> workerDtoSet) {
        this.workerDtoSet = workerDtoSet;
    }
}
