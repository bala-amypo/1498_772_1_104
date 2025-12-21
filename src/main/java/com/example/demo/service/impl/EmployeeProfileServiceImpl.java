package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeProfile saveEmployee(EmployeeProfile employee) {
        return repo.save(employee);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeProfile> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public EmployeeProfile updateEmployee(Long id, EmployeeProfile employee) {
        Optional<EmployeeProfile> existing = repo.findById(id);
        if(existing.isPresent()){
            EmployeeProfile emp = existing.get();
            emp.setName(employee.getName());
            emp.setDepartment(employee.getDepartment());
            emp.setEmail(employee.getEmail());
            emp.setActive(employee.getActive());
            return repo.save(emp);
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        repo.deleteById(id);
    }
}
