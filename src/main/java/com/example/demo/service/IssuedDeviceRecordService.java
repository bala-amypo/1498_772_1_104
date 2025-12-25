package com.example.demo.service;

import com.example.demo.model.IssuedDeviceRecord;

import java.util.List;

public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long recordId);

    List<IssuedDeviceRecord> getAllIssuedDevices();

    List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId);

    List<IssuedDeviceRecord> getActiveIssuedDevices();
}
