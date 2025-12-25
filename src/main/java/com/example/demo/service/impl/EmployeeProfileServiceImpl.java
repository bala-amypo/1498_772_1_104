package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;

import java.util.List;
import java.util.Optional;

public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeProfileRepository;

    // ✅ Constructor Injection ONLY
    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeProfileRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employee) {

        // 🔴 Duplicate Employee ID check
        Optional<EmployeeProfile> byEmployeeId =
                employeeProfileRepository.findByEmployeeId(employee.getEmployeeId());
        if (byEmployeeId.isPresent()) {
            throw new BadRequestException("EmployeeId already exists");
        }

        // 🔴 Duplicate Email check
        Optional<EmployeeProfile> byEmail =
                employeeProfileRepository.findByEmail(employee.getEmail());
        if (byEmail.isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        // Default job role
        if (employee.getJobRole() == null) {
            employee.setJobRole("STAFF");
        }

        return employeeProfileRepository.save(employee);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return employeeProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeProfile> getAllEmployees() {
        return employeeProfileRepository.findAll();
    }

    @Override
    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {

        EmployeeProfile employee = employeeProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employee.setActive(active);
        return employeeProfileRepository.save(employee);
    }
}
