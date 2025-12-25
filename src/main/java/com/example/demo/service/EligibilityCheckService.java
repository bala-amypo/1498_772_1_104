package com.example.demo.service;

import com.example.demo.model.EligibilityCheckRecord;

import java.util.List;

public interface EligibilityCheckService {

    EligibilityCheckRecord saveCheck(EligibilityCheckRecord record);

    List<EligibilityCheckRecord> getAllChecks();

    List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId);

    List<EligibilityCheckRecord> getChecksByDevice(Long deviceId);

    List<EligibilityCheckRecord> getEligibleChecks();
}
