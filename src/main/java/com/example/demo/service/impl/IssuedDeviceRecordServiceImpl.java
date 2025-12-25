package com.example.demo.service.impl;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository repository;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
        record.setIssuedAt(LocalDateTime.now());
        record.setActive(true);
        return repository.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long id) {
        IssuedDeviceRecord record = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Issued device not found with id: " + id));
        record.setActive(false);
        record.setReturnedAt(LocalDateTime.now());
        return repository.save(record);
    }

    @Override
    public List<IssuedDeviceRecord> getAllIssuedDevices() {
        return repository.findAll();
    }

    @Override
    public List<IssuedDeviceRecord> getActiveIssuedDevices() {
        return repository.findByActiveTrue();
    }

    @Override
    public IssuedDeviceRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Issued device not found with id: " + id));
    }
}
