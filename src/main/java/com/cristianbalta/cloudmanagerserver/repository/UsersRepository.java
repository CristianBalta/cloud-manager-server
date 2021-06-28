package com.cristianbalta.cloudmanagerserver.repository;

import com.cristianbalta.cloudmanagerserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);
}
