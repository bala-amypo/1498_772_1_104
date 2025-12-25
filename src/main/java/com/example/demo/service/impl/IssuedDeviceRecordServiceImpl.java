package com.example.demo.service;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository issuedDeviceRecordRepository;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository issuedDeviceRecordRepository) {
        this.issuedDeviceRecordRepository = issuedDeviceRecordRepository;
    }

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
        // Pre-checks (active employee, active device, etc.) should be done in Controller or separate EligibilityService
        record.setIssuedDate(LocalDate.now());
        record.setStatus("ISSUED");
        return issuedDeviceRecordRepository.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {
        IssuedDeviceRecord record = issuedDeviceRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("IssuedDeviceRecord not found"));

        record.setReturnedDate(LocalDate.now());
        record.setStatus("RETURNED");

        return issuedDeviceRecordRepository.save(record);
    }

    @Override
    public List<IssuedDeviceRecord> getAllIssuedDevices() {
        return issuedDeviceRecordRepository.findAll();
    }

    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return issuedDeviceRecordRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<IssuedDeviceRecord> getActiveIssuedDevices() {
        return issuedDeviceRecordRepository.findByStatus("ISSUED");
    }
}
