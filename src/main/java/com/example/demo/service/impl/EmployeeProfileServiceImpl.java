package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProfileServiceImpl
        implements EmployeeProfileService {

    @Autowired
    private EmployeeProfileRepository employeeRepository;

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employee) {

        if (employeeRepository.findByEmployeeId(employee.getEmployeeId()).isPresent()) {
            throw new BadRequestException("EmployeeId already exists");
        }

        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        if (employee.getJobRole() == null) {
            employee.setJobRole("STAFF");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeProfile> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {

        EmployeeProfile employee = getEmployeeById(id);
        employee.setActive(active);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

        EmployeeProfile employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
