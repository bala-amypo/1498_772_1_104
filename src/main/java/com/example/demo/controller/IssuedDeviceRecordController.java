package com.example.demo.controller;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
@Tag(name = "Issued Device Records Endpoints")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService issuedDeviceRecordService;

    // ✅ Constructor Injection ONLY
    public IssuedDeviceRecordController(IssuedDeviceRecordService issuedDeviceRecordService) {
        this.issuedDeviceRecordService = issuedDeviceRecordService;
    }

    @Operation(summary = "Issue a device to an employee")
    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> issueDevice(
            @RequestBody IssuedDeviceRecord record) {

        IssuedDeviceRecord issued = issuedDeviceRecordService.issueDevice(record);
        return new ResponseEntity<>(issued, HttpStatus.CREATED);
    }

    @Operation(summary = "Return an issued device")
    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {

        IssuedDeviceRecord returned = issuedDeviceRecordService.returnDevice(id);
        return ResponseEntity.ok(returned);
    }

    @Operation(summary = "Get all issued devices")
    @GetMapping
    public ResponseEntity<List<IssuedDeviceRecord>> getAllIssuedDevices() {
        List<IssuedDeviceRecord> all = issuedDeviceRecordService.getAllIssuedDevices();
        return ResponseEntity.ok(all);
    }

    @Operation(summary = "Get all issued devices for a specific employee")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<IssuedDeviceRecord>> getIssuedDevicesByEmployee(
            @PathVariable Long employeeId) {

        List<IssuedDeviceRecord> records =
                issuedDeviceRecordService.getIssuedDevicesByEmployee(employeeId);
        return ResponseEntity.ok(records);
    }

    @Operation(summary = "Get all active issued devices")
    @GetMapping("/active")
    public ResponseEntity<List<IssuedDeviceRecord>> getActiveIssuedDevices() {
        List<IssuedDeviceRecord> active = issuedDeviceRecordService.getActiveIssuedDevices();
        return ResponseEntity.ok(active);
    }
}
