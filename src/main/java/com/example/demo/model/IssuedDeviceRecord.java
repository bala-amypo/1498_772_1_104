package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "issued_device_records")
public class IssuedDeviceRecord {

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
    private LocalDateTime issuedAt;

    private LocalDateTime returnedAt;

    @Column(nullable = false)
    private boolean active; // true if device is currently issued

    // ✅ Constructors
    public IssuedDeviceRecord() {}

    public IssuedDeviceRecord(EmployeeProfile employee, DeviceCatalogItem device, LocalDateTime issuedAt, boolean active) {
        this.employee = employee;
        this.device = device;
        this.issuedAt = issuedAt;
        this.active = active;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public EmployeeProfile getEmployee() { return employee; }
    public void setEmployee(EmployeeProfile employee) { this.employee = employee; }
    public DeviceCatalogItem getDevice() { return device; }
    public void setDevice(DeviceCatalogItem device) { this.device = device; }
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
    public LocalDateTime getReturnedAt() { return returnedAt; }
    public void setReturnedAt(LocalDateTime returnedAt) { this.returnedAt = returnedAt; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
