package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.IssuedDeviceRecord;

public interface IssuedDeviceRecordService {
    
    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);
    IssuedDeviceRecord returnDevice(Long recordId);
    Optional<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId);

}
