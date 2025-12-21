package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_check_records")
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Relationships */

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    @ManyToOne
    @JoinColumn(name = "device_item_id", nullable = false)
    private DeviceCatalogItem deviceItem;

    @NotNull
    @Column(nullable = false)
    private Boolean isEligible;

    @Column(nullable = false, length = 1000)
    private String reason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime checkedAt;

   

    public EligibilityCheckRecord() {}

    public EligibilityCheckRecord(
            EmployeeProfile employee,
            DeviceCatalogItem deviceItem,
            Boolean isEligible,
            String reason
    ) {
        this.employee = employee;
        this.deviceItem = deviceItem;
        this.isEligible = isEligible;
        this.reason = reason;
    }

    @PrePersist
    protected void onCreate() {
        this.checkedAt = LocalDateTime.now();
    }


    public Long getId() { return id; }

    public EmployeeProfile getEmployee() { return employee; }
    public void setEmployee(EmployeeProfile employee) { this.employee = employee; }

    public DeviceCatalogItem getDeviceItem() { return deviceItem; }
    public void setDeviceItem(DeviceCatalogItem deviceItem) { this.deviceItem = deviceItem; }

    public Boolean getIsEligible() { return isEligible; }
    public void setIsEligible(Boolean eligible) { isEligible = eligible; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getCheckedAt() { return checkedAt; }
}
