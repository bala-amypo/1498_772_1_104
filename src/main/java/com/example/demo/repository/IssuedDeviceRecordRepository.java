package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    /* CRITICAL: active ISSUED records for employee & device */
    @Query("""
        SELECT r FROM IssuedDeviceRecord r
        WHERE r.employee.id = :employeeId
          AND r.deviceItem.id = :deviceItemId
          AND r.status = 'ISSUED'
    """)
    List<IssuedDeviceRecord> findActiveByEmployeeAndDevice(
            Long employeeId,
            Long deviceItemId
    );

    @Query("""
        SELECT COUNT(r) FROM IssuedDeviceRecord r
        WHERE r.employee.id = :employeeId
          AND r.status = 'ISSUED'
    """)
    Long countActiveDevicesForEmployee(Long employeeId);

    @Query("""
        SELECT r FROM IssuedDeviceRecord r
        WHERE r.employee.id = :employeeId
    """)
    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);
}
