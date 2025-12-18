package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.repository.EligibilityCheckRepository;

@Service
public class EligibilityCheckServiceImpl
        implements EligibilityCheckService {

    private final EligibilityCheckRepository repo;

    public EligibilityCheckServiceImpl(EligibilityCheckRepository repo) {
        this.repo = repo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {
        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);
        record.setEligible(true); // simplified logic
        return repo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
        }