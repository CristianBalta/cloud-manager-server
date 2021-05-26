package com.cristianbalta.cloudmanagerserver.entity.compositekeys;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class UserWorkerLinkId implements Serializable {

    @Column(name = "worker_ip")
    private String workerIp;

    @Column(name = "user_email")
    private String userEmail;

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
}
