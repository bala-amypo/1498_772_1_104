package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssuedDeviceRecordServiceImpl
        implements IssuedDeviceRecordService {

    @Autowired
    private IssuedDeviceRecordRepository issuedRepository;

    @Autowired
    private EmployeeProfileRepository employeeRepository;

    @Autowired
    private DeviceCatalogItemRepository deviceRepository;

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {

        Long employeeId = record.getEmployee().getId();
        Long deviceItemId = record.getDeviceItem().getId();

        EmployeeProfile employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        DeviceCatalogItem device = deviceRepository.findById(deviceItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Device not found"));

        List<IssuedDeviceRecord> activeRecords =
                issuedRepository.findActiveByEmployeeAndDevice(employeeId, deviceItemId);

        if (!activeRecords.isEmpty()) {
            throw new BadRequestException("Device already issued");
        }

        record.setEmployee(employee);
        record.setDeviceItem(device);
        record.setIssuedDate(LocalDate.now());
        record.setStatus("ISSUED");
        record.setReturnedDate(null);

        return issuedRepository.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {

        IssuedDeviceRecord record = issuedRepository.findById(recordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Issued device record not found"));

        if ("RETURNED".equals(record.getStatus())) {
            throw new BadRequestException("already returned");
        }

        record.setReturnedDate(LocalDate.now());
        record.setStatus("RETURNED");

        return issuedRepository.save(record);
    }

    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return issuedRepository.findByEmployeeId(employeeId);
    }

    @Override
    public IssuedDeviceRecord getIssuedDeviceById(Long id) {
        return issuedRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Issued device record not found"));
    }
}
