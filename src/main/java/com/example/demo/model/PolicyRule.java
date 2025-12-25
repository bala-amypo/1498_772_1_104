package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "policy_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruleCode")
    }
)
public class PolicyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleCode;

    @Column
    private String description;

    @Column
    private String appliesToRole; // ADMIN / DEVELOPER / MANAGER / STAFF / null for all

    @Column
    private String appliesToDepartment; // IT / HR / SALES / FINANCE / null for all

    @Column(nullable = false)
    private Integer maxDevicesAllowed;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Default constructor
    public PolicyRule() {
        this.active = true;
    }

    // Parameterized constructor
    public PolicyRule(String ruleCode, String description, String appliesToRole, String appliesToDepartment, Integer maxDevicesAllowed, Boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.appliesToRole = appliesToRole;
        this.appliesToDepartment = appliesToDepartment;
        this.maxDevicesAllowed = maxDevicesAllowed;
        this.active = (active != null) ? active : true;
    }

    // PrePersist to auto-set createdAt
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppliesToRole() {
        return appliesToRole;
    }

    public void setAppliesToRole(String appliesToRole) {
        this.appliesToRole = appliesToRole;
    }

    public String getAppliesToDepartment() {
        return appliesToDepartment;
    }

    public void setAppliesToDepartment(String appliesToDepartment) {
        this.appliesToDepartment = appliesToDepartment;
    }

    public Integer getMaxDevicesAllowed() {
        return maxDevicesAllowed;
    }

    public void setMaxDevicesAllowed(Integer maxDevicesAllowed) {
        this.maxDevicesAllowed = maxDevicesAllowed;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
