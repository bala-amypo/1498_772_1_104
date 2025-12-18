package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeProfile;

@Service
public interface EmployeeProfileService {
    
    EmployeeProfile createEmployee(EmployeeProfile employee);
    Optional<EmployeeProfile> getEmployeeById(Long id);
    List<EmployeeProfile> getAllEmployee();
    EmployeeProfile UpdateEmployeeStatus(Long id,Boolean active);

    
}
