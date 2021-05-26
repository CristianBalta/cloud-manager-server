package com.cristianbalta.cloudmanagerserver.repository;

import com.cristianbalta.cloudmanagerserver.entity.Worker;
import com.cristianbalta.cloudmanagerserver.entity.compositekeys.UserWorkerLinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkersRepository extends JpaRepository<Worker, UserWorkerLinkId> {
}
