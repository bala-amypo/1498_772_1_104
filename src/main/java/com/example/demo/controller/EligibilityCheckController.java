package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eligibility")
@Tag(name = "Eligibility Check Endpoints")
public class EligibilityCheckController {

    @Autowired
    private EligibilityCheckService eligibilityCheckService;

    @Operation(summary = "Validate eligibility for device issuance")
    @PostMapping("/validate/{employeeId}/{deviceItemId}")
    public ResponseEntity<EligibilityCheckRecord> validateEligibility(
            @PathVariable Long employeeId,
            @PathVariable Long deviceItemId
    ) {
        return ResponseEntity.ok(
                eligibilityCheckService.validateEligibility(employeeId, deviceItemId)
        );
    }

    @Operation(summary = "Get all eligibility checks for an employee")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EligibilityCheckRecord>> getChecksByEmployee(
            @PathVariable Long employeeId
    ) {
        return ResponseEntity.ok(
                eligibilityCheckService.getChecksByEmployee(employeeId)
        );
    }

    @Operation(summary = "Get eligibility check record by ID")
    @GetMapping("/{checkId}")
    public ResponseEntity<EligibilityCheckRecord> getCheckById(
            @PathVariable Long checkId
    ) {
        return ResponseEntity.ok(
                eligibilityCheckService.getChecksByEmployee(checkId)
                        .stream()
                        .findFirst()
                        .orElseThrow()
        );
    }
}
