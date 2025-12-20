package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

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

    @NotBlank
    @Column(nullable = false, unique = true)
    private String ruleCode;

    @Column
    private String description;

    @Column
    private String appliesToRole; // ADMIN / DEVELOPER / MANAGER / STAFF / null

    @Column
    private String appliesToDepartment; // IT / HR / SALES / FINANCE / null

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer maxDevicesAllowed;

    @Column(nullable = false)
    private Boolean active = true;

    /* Constructors */

    public PolicyRule() {
    }

    public PolicyRule(
            String ruleCode,
            Integer maxDevicesAllowed,
            String appliesToRole,
            String appliesToDepartment
    ) {
        this.ruleCode = ruleCode;
        this.maxDevicesAllowed = maxDevicesAllowed;
        this.appliesToRole = appliesToRole;
        this.appliesToDepartment = appliesToDepartment;
        this.active = true;
    }

    /* Getters & Setters */

    public Long getId() { return id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAppliesToRole() { return appliesToRole; }
    public void setAppliesToRole(String appliesToRole) {
        this.appliesToRole = appliesToRole;
    }

    public String getAppliesToDepartment() { return appliesToDepartment; }
    public void setAppliesToDepartment(String appliesToDepartment) {
        this.appliesToDepartment = appliesToDepartment;
    }

    public Integer getMaxDevicesAllowed() { return maxDevicesAllowed; }
    public void setMaxDevicesAllowed(Integer maxDevicesAllowed) {
        this.maxDevicesAllowed = maxDevicesAllowed;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
