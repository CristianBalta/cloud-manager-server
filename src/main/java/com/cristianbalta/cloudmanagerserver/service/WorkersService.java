package com.cristianbalta.cloudmanagerserver.service;

import com.cristianbalta.cloudmanagerserver.dto.WorkerDto;
import com.cristianbalta.cloudmanagerserver.entity.User;
import com.cristianbalta.cloudmanagerserver.entity.Worker;
import com.cristianbalta.cloudmanagerserver.entity.compositekeys.UserWorkerLinkId;
import com.cristianbalta.cloudmanagerserver.repository.WorkersRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkersService {

    private final WorkersRepository workersRepository;

    public WorkersService(WorkersRepository workersRepository) {
        this.workersRepository = workersRepository;
    }

    public void createWorker(WorkerDto workerDto) throws Exception {

        UserWorkerLinkId userWorkerLinkId = new UserWorkerLinkId();
        userWorkerLinkId.setUserEmail(workerDto.getUserEmail());
        userWorkerLinkId.setWorkerIp(workerDto.getWorkerIp());

        Worker worker = workersRepository.findByUserWorkerLinkId(userWorkerLinkId);

        if (worker != null) {
            throw new Exception("Worker already exists!");
        } else {
            worker = new Worker();
            worker.setUserWorkerLinkId(userWorkerLinkId);
            worker.setWorkerSecret(workerDto.getWorkerSecret());
            workersRepository.save(worker);
        }
    }
}
