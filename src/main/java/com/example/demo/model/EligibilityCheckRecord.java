package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_check_records")
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceCatalogItem device;

    @Column(nullable = false)
    private boolean eligible; // true if employee passed all policy checks

    @Column(nullable = false)
    private LocalDateTime checkedAt;

    private String reason; // reason for ineligibility, null if eligible

    // ✅ Constructors
    public EligibilityCheckRecord() {}

    public EligibilityCheckRecord(EmployeeProfile employee, DeviceCatalogItem device, boolean eligible, LocalDateTime checkedAt, String reason) {
        this.employee = employee;
        this.device = device;
        this.eligible = eligible;
        this.checkedAt = checkedAt;
        this.reason = reason;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public EmployeeProfile getEmployee() { return employee; }
    public void setEmployee(EmployeeProfile employee) { this.employee = employee; }
    public DeviceCatalogItem getDevice() { return device; }
    public void setDevice(DeviceCatalogItem device) { this.device = device; }
    public boolean isEligible() { return eligible; }
    public void setEligible(boolean eligible) { this.eligible = eligible; }
    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
