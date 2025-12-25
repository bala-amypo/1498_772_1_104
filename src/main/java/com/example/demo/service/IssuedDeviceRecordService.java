package com.example.demo.service;

import com.example.demo.model.IssuedDeviceRecord;

import java.util.List;

public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long id);

    List<IssuedDeviceRecord> getAllIssuedDevices();

    List<IssuedDeviceRecord> getActiveIssuedDevices();

    IssuedDeviceRecord getById(Long id);
}
