package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityCheckServiceImpl
        implements EligibilityCheckService {

    @Autowired
    private EmployeeProfileRepository employeeRepository;

    @Autowired
    private DeviceCatalogItemRepository deviceRepository;

    @Autowired
    private IssuedDeviceRecordRepository issuedRepository;

    @Autowired
    private PolicyRuleRepository policyRuleRepository;

    @Autowired
    private EligibilityCheckRecordRepository eligibilityRepository;

    @Override
    public EligibilityCheckRecord validateEligibility(
            Long employeeId,
            Long deviceItemId
    ) {

        EmployeeProfile employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        DeviceCatalogItem device = deviceRepository.findById(deviceItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Device not found"));

        String reason;
        boolean eligible = true;

        /* a. employee active check */
        if (!employee.getActive()) {
            eligible = false;
            reason = "Employee is inactive";
            return save(employee, device, eligible, reason);
        }

        /* b. device active check */
        if (!device.getActive()) {
            eligible = false;
            reason = "Device is inactive";
            return save(employee, device, eligible, reason);
        }

        /* c. duplicate active issuance check */
        if (!issuedRepository
                .findActiveByEmployeeAndDevice(employeeId, deviceItemId)
                .isEmpty()) {

            eligible = false;
            reason = "Device already issued to employee";
            return save(employee, device, eligible, reason);
        }

        /* d. device maxAllowedPerEmployee check */
        Long activeCount =
                issuedRepository.countActiveDevicesForEmployee(employeeId);

        if (activeCount >= device.getMaxAllowedPerEmployee()) {
            eligible = false;
            reason = "Device limit exceeded for employee";
            return save(employee, device, eligible, reason);
        }

        /* e–f. policy rule checks */
        List<PolicyRule> activeRules =
                policyRuleRepository.findByActiveTrue();

        for (PolicyRule rule : activeRules) {

            boolean roleMatch =
                    rule.getAppliesToRole() == null ||
                    rule.getAppliesToRole().equals(employee.getJobRole());

            boolean deptMatch =
                    rule.getAppliesToDepartment() == null ||
                    rule.getAppliesToDepartment().equals(employee.getDepartment());

            if (roleMatch && deptMatch) {
                if (activeCount >= rule.getMaxDevicesAllowed()) {
                    eligible = false;
                    reason = "Policy rule violated: " + rule.getRuleCode();
                    return save(employee, device, eligible, reason);
                }
            }
        }

        /* g. eligible case */
        reason = "Employee eligible for device issuance";
        return save(employee, device, true, reason);
    }

    private EligibilityCheckRecord save(
            EmployeeProfile employee,
            DeviceCatalogItem device,
            boolean eligible,
            String reason
    ) {
        EligibilityCheckRecord record =
                new EligibilityCheckRecord(employee, device, eligible, reason);
        return eligibilityRepository.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityRepository.findByEmployeeId(employeeId);
    }
}
