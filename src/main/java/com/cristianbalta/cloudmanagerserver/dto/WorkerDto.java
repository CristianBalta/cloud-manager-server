package com.cristianbalta.cloudmanagerserver.dto;

public class WorkerDto {

    private String workerIp;

    private String userEmail;

    private String workerSecret;

    private UserDto userDto;

    public String getWorkerIp() {
        return workerIp;
    }

    public void setWorkerIp(String workerIp) {
        this.workerIp = workerIp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getWorkerSecret() {
        return workerSecret;
    }

    public void setWorkerSecret(String workerSecret) {
        this.workerSecret = workerSecret;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
