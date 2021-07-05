package com.cristianbalta.cloudmanagerserver.dto;

import java.util.Set;

public class UserDto {

    private Long userId;

    private String userEmail;

    private String userPassword;

    private Set<String> workerIpSet;

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

    public Set<String> getWorkerIpSet() {
        return workerIpSet;
    }

    public void setWorkerIpSet(Set<String> workerIpSet) {
        this.workerIpSet = workerIpSet;
    }
}
