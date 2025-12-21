package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "employee_profiles",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "employeeld"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeld;
    private String fullName;
    private String email;
    private String department;
    private String jobRole;
    private Boolean active;
    private LocalDateTime createdAt;

    // ✅ Default constructor
    public EmployeeProfile() {
        this.active = true;
    }

    // ✅ Parameterized constructor
    public EmployeeProfile(String employeeld, String fullName, String email,
                           String department, String jobRole, Boolean active) {
        this.employeeld = employeeld;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.jobRole = jobRole;
        this.active = active;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public String getEmployeeld() {
        return employeeld;
    }

    public void setEmployeeld(String employeeld) {
        this.employeeld = employeeld;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobRole() {
        return jobRole;
    }
    
    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
