package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eligibility-checks")
@Tag(name = "Eligibility Check Endpoints")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    @Operation(summary = "Save an eligibility check record")
    @PostMapping
    public ResponseEntity<EligibilityCheckRecord> saveCheck(@RequestBody EligibilityCheckRecord record) {
        EligibilityCheckRecord saved = service.saveCheck(record);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all eligibility check records")
    @GetMapping
    public ResponseEntity<List<EligibilityCheckRecord>> getAllChecks() {
        return ResponseEntity.ok(service.getAllChecks());
    }

    @Operation(summary = "Get eligibility checks by employee")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EligibilityCheckRecord>> getChecksByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getChecksByEmployee(employeeId));
    }

    @Operation(summary = "Get eligibility checks by device")
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<EligibilityCheckRecord>> getChecksByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(service.getChecksByDevice(deviceId));
    }

    @Operation(summary = "Get all eligible checks")
    @GetMapping("/eligible")
    public ResponseEntity<List<EligibilityCheckRecord>> getEligibleChecks() {
        return ResponseEntity.ok(service.getEligibleChecks());
    }
}
