package com.cristianbalta.cloudmanagerserver.entity;

import com.cristianbalta.cloudmanagerserver.entity.compositekeys.UserWorkerLinkId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "workers")
public class Worker {

    @EmbeddedId
    private UserWorkerLinkId userWorkerLinkId = new UserWorkerLinkId();

    @ManyToOne
    @MapsId("userEmail")
    @JoinColumn(name = "user_email")
    private User user;

    @NotBlank(message = "Worker ip can't be blank.")
    @Column(name = "worker_ip")
    private String workerIp;

    @NotBlank(message = "Worker secret can't be blank.")
    @Column(name = "woker_Secret")
    private String workerSecret;

    public UserWorkerLinkId getUserWorkerLinkId() {
        return userWorkerLinkId;
    }

    public void setUserWorkerLinkId(UserWorkerLinkId userWorkerLinkId) {
        this.userWorkerLinkId = userWorkerLinkId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWorkerIp() {
        return workerIp;
    }

    public void setWorkerIp(String workerIp) {
        this.workerIp = workerIp;
    }

    public String getWorkerSecret() {
        return workerSecret;
    }

    public void setWorkerSecret(String workerSecret) {
        this.workerSecret = workerSecret;
    }
}
