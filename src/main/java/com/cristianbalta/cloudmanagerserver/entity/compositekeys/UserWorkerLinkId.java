package com.cristianbalta.cloudmanagerserver.entity.compositekeys;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class UserWorkerLinkId implements Serializable {

    @NotNull(message = "Worker id can't be blank.")
    @Column(name = "worker_id")
    Long workerId;

    @NotNull(message = "User email can't be blank.")
    @Column(name = "user_email")
    String userEmail;

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
