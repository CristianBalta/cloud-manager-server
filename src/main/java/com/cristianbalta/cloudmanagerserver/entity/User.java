package com.cristianbalta.cloudmanagerserver.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "User email can't be blank.")
    @Column(name = "user_email")
    private String userEmail;

    @NotBlank(message = "User password can't be blank.")
    @Column(name = "user_password")
    private String userPassword;

    @OneToMany(targetEntity = Worker.class, cascade = ALL, mappedBy = "user")
    private Set<Worker> workerSet;

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

    public Set<Worker> getWorkerSet() {
        return workerSet;
    }

    public void setWorkerSet(Set<Worker> workerSet) {
        this.workerSet = workerSet;
    }
}
