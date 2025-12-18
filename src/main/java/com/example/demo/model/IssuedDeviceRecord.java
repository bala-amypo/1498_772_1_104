package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class IssuedDeviceRecord {
    @Id
    private Long id;
    private Long employeeId;
    private Long deviceItemId;
    public IssuedDeviceRecord() {
    }
    private LocalDate issueDate;
    public IssuedDeviceRecord(Long id, Long employeeId, Long deviceItemId, LocalDate issueDate, LocalDate returnedDate,
            String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.deviceItemId = deviceItemId;
        this.issueDate = issueDate;
        this.returnedDate = returnedDate;
        this.status = status;
    }
    private LocalDate returnedDate;
    private String status;
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
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public LocalDate getReturnedDate() {
        return returnedDate;
    }
    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
