package com.example.demo.service.impl;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.repository.EligibilityCheckRecordRepository;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EligibilityCheckRecordRepository repository;

    public EligibilityCheckServiceImpl(EligibilityCheckRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public EligibilityCheckRecord saveCheck(EligibilityCheckRecord record) {
        return repository.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getAllChecks() {
        return repository.findAll();
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByDevice(Long deviceId) {
        return repository.findByDeviceId(deviceId);
    }

    @Override
    public List<EligibilityCheckRecord> getEligibleChecks() {
        return repository.findByEligibleTrue();
    }
}
