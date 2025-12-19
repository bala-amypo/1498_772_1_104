package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long deviceItemId;
    public EligibilityCheckRecord(Long id, Long employeeId, Long deviceItemId, Boolean eligible) {
        this.id = id;
        this.employeeId = employeeId;
        this.deviceItemId = deviceItemId;
        this.eligible = eligible;
    }
    public EligibilityCheckRecord() {
    }
    private Boolean eligible;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public Long getDeviceItemId() {
        return deviceItemId;
    }
    public void setDeviceItemId(Long deviceItemId) {
        this.deviceItemId = deviceItemId;
    }
    public Boolean getEligible() {
        return eligible;
    }
    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    
}