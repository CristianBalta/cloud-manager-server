package com.cristianbalta.cloudmanagerserver.repository;

import com.cristianbalta.cloudmanagerserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);

    @Modifying
    @Query(value = "UPDATE users SET user_worker_bearer_token = (?1) WHERE user_email = (?2);", nativeQuery = true)
    void saveNewBearer(String userWorkerBearerToken, String userEmail);
}
