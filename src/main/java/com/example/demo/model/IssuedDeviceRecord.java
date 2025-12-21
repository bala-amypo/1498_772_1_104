package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "issued_device_records")
public class IssuedDeviceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    // 🔹 Just store device item id
    @Column(name = "device_item_id", nullable = false)
    private Long deviceItemId;

    @NotNull
    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column
    private LocalDate returnedDate;

    @Column(nullable = false)
    private String status = "ISSUED";

    public IssuedDeviceRecord() {}

    public IssuedDeviceRecord(Long employeeId, Long deviceItemId) {
        this.employeeId = employeeId;
        this.deviceItemId = deviceItemId;
        this.issuedDate = LocalDate.now();
        this.status = "ISSUED";
    }

    /* Getters & Setters */

    public Long getId() { return id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long getDeviceItemId() { return deviceItemId; }
    public void setDeviceItemId(Long deviceItemId) { this.deviceItemId = deviceItemId; }

    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    public LocalDate getReturnedDate() { return returnedDate; }
    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
        if (returnedDate != null) {
            this.status = "RETURNED";
        }
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
