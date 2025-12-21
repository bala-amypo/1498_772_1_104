package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.EmployeeProfile;

public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    // ❗ EXACT name required by test suite
    EmployeeProfile findByEmployeeld(String employeeld);
}
