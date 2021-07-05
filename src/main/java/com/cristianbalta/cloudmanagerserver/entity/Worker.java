package com.cristianbalta.cloudmanagerserver.entity;

import com.cristianbalta.cloudmanagerserver.entity.compositekeys.UserWorkerLinkId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {

    @EmbeddedId
    private UserWorkerLinkId userWorkerLinkId = new UserWorkerLinkId();

    public UserWorkerLinkId getUserWorkerLinkId() {
        return userWorkerLinkId;
    }

    public void setUserWorkerLinkId(UserWorkerLinkId userWorkerLinkId) {
        this.userWorkerLinkId = userWorkerLinkId;
    }
}
