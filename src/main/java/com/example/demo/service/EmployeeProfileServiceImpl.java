package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService{

    
    private final EmployeeProfileRepository repo;
    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo){
        this.repo=repo;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employee){
        return repo.save(employee);
    }

    @Override
    public Optional<EmployeeProfile> getEmployeeById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<EmployeeProfile> getAllEmployee() {
        return repo.findAll();
    }

    @Override
    public EmployeeProfile UpdateEmployeeStatus(Long id, Boolean active) {
        EmployeeProfile emp=repo.findById(id).orElseThrow();
        emp.setActive(active);
        return repo.save(emp);
    }

}
