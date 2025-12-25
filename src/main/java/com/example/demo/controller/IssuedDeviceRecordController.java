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
@Tag(name = "Issued Device Endpoints")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService service;

    public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
        this.service = service;
    }

    @Operation(summary = "Issue a device to an employee")
    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> issueDevice(@RequestBody IssuedDeviceRecord record) {
        IssuedDeviceRecord issued = service.issueDevice(record);
        return new ResponseEntity<>(issued, HttpStatus.CREATED);
    }

    @Operation(summary = "Return a device")
    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        IssuedDeviceRecord returned = service.returnDevice(id);
        return ResponseEntity.ok(returned);
    }

    @Operation(summary = "Get all issued devices")
    @GetMapping
    public ResponseEntity<List<IssuedDeviceRecord>> getAllIssuedDevices() {
        return ResponseEntity.ok(service.getAllIssuedDevices());
    }

    @Operation(summary = "Get all active issued devices")
    @GetMapping("/active")
    public ResponseEntity<List<IssuedDeviceRecord>> getActiveIssuedDevices() {
        return ResponseEntity.ok(service.getActiveIssuedDevices());
    }

    @Operation(summary = "Get issued device by ID")
    @GetMapping("/{id}")
    public ResponseEntity<IssuedDeviceRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
