package com.example.demo.controller;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
@Tag(name = "Issued Device Records Endpoints")
public class IssuedDeviceRecordController {

    @Autowired
    private IssuedDeviceRecordService issuedService;

    @Operation(summary = "Issue a device to an employee")
    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> issueDevice(
            @Valid @RequestBody IssuedDeviceRecord record
    ) {
        return ResponseEntity.ok(issuedService.issueDevice(record));
    }

    @Operation(summary = "Return an issued device")
    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(issuedService.returnDevice(id));
    }

    @Operation(summary = "Get issued devices by employee")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<IssuedDeviceRecord>> getIssuedDevicesByEmployee(
            @PathVariable Long employeeId
    ) {
        return ResponseEntity.ok(
                issuedService.getIssuedDevicesByEmployee(employeeId)
        );
    }

    @Operation(summary = "Get issued device record by ID")
    @GetMapping("/{id}")
    public ResponseEntity<IssuedDeviceRecord> getIssuedDeviceById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                issuedService.getIssuedDeviceById(id)
        );
    }
}
