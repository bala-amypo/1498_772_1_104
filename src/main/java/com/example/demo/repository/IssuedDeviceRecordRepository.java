package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {

    List<IssuedDeviceRecord> findByEmployeeIdAndActiveTrue(Long employeeId);

    List<IssuedDeviceRecord> findByDeviceIdAndActiveTrue(Long deviceId);

    List<IssuedDeviceRecord> findByActiveTrue();
}
