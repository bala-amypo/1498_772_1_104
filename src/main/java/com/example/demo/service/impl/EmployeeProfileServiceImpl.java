package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeProfileRepository;

    // Constructor Injection (NO @Autowired)
    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeProfileRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employeeProfile) {

        // Validate employeeId uniqueness
        Optional<EmployeeProfile> existingByEmployeeId =
                employeeProfileRepository.findByEmployeeId(employeeProfile.getEmployeeId());
        if (existingByEmployeeId.isPresent()) {
            throw new BadRequestException("Employee ID already exists");
        }

        // Validate email uniqueness
        Optional<EmployeeProfile> existingByEmail =
                employeeProfileRepository.findByEmail(employeeProfile.getEmail());
        if (existingByEmail.isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        // Default values
        if (employeeProfile.getJobRole() == null) {
            employeeProfile.setJobRole("STAFF");
        }

        employeeProfile.setActive(true);

        return employeeProfileRepository.save(employeeProfile);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return employeeProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public List<EmployeeProfile> getAllEmployees() {
        return employeeProfileRepository.findAll();
    }

    @Override
    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {

        EmployeeProfile employee = employeeProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setActive(active);
        return employeeProfileRepository.save(employee);
    }
}
