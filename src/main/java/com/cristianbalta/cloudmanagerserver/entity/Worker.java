package com.cristianbalta.cloudmanagerserver.entity;

import com.cristianbalta.cloudmanagerserver.entity.compositekeys.UserWorkerLinkId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {

    @EmbeddedId
    private UserWorkerLinkId userWorkerLinkId = new UserWorkerLinkId();

    @NotBlank(message = "Worker secret can't be blank.")
    @Column(name = "worker_secret")
    private String workerSecret;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false, updatable = false, insertable = false)
    private User user;

    public UserWorkerLinkId getUserWorkerLinkId() {
        return userWorkerLinkId;
    }

    public void setUserWorkerLinkId(UserWorkerLinkId userWorkerLinkId) {
        this.userWorkerLinkId = userWorkerLinkId;
    }

    public String getWorkerSecret() {
        return workerSecret;
    }

    public void setWorkerSecret(String workerSecret) {
        this.workerSecret = workerSecret;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
