package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "issued_device_records")
public class IssuedDeviceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationships
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    @ManyToOne
    @JoinColumn(name = "device_item_id", nullable = false)
    private DeviceCatalogItem deviceItem;

    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column
    private LocalDate returnedDate;

    @Column(nullable = false)
    private String status; // ISSUED / RETURNED

    /* ================== CONSTRUCTORS ================== */

    public IssuedDeviceRecord() {
        // default constructor
    }

    public IssuedDeviceRecord(EmployeeProfile employee,
                              DeviceCatalogItem deviceItem,
                              LocalDate issuedDate,
                              LocalDate returnedDate,
                              String status) {
        this.employee = employee;
        this.deviceItem = deviceItem;
        this.issuedDate = issuedDate;
        this.returnedDate = returnedDate;
        this.status = (status != null) ? status : "ISSUED";
    }

    /* ================== LIFECYCLE CALLBACK ================== */

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = "ISSUED";
        }
        if (this.issuedDate == null) {
            this.issuedDate = LocalDate.now();
        }
    }

    /* ================== GETTERS & SETTERS ================== */

    public Long getId() {
        return id;
    }

    public EmployeeProfile getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
    }

    public DeviceCatalogItem getDeviceItem() {
        return deviceItem;
    }

    public void setDeviceItem(DeviceCatalogItem deviceItem) {
        this.deviceItem = deviceItem;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
        if (returnedDate != null) {
            this.status = "RETURNED";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = (status != null) ? status : "ISSUED";
    }
}
