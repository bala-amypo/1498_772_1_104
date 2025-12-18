package com.example.demo.service;

import java.time.LocalDate;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService{
    
    private final IssuedDeviceRecordRepository repo;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository repo){
        this.repo=repo;
    }

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
        record.setIssueDate(LocalDate.now());
        record.setStatus("ISSUED");
        return repo.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {
        IssuedDeviceRecord record = repo.findById(recordId).orElseThrow();
        if ("RETURNED".equals(record.getStatus())) {
            throw new RuntimeException("Device already returned");
        }
        record.setReturnedDate(LocalDate.now());
        record.setStatus("RETURNED");
        return repo.save(record);
    }

    @Override
    public Optional<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
       return repo.findById(employeeId);
    }

    
}
