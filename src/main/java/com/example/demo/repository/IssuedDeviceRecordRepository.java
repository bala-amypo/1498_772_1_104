package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {

    // Find all issued devices for a specific employee
    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);

    // Find all active issued devices
    List<IssuedDeviceRecord> findByStatus(String status);

    // Find by employee and device item (optional for eligibility checks)
    List<IssuedDeviceRecord> findByEmployeeIdAndDeviceItemIdAndStatus(Long employeeId, Long deviceItemId, String status);
}
