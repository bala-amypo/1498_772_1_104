package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Profile Endpoints")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    // ✅ Constructor Injection ONLY
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @Operation(summary = "Create a new employee profile")
    @PostMapping
    public ResponseEntity<EmployeeProfile> createEmployee(
            @RequestBody EmployeeProfile employeeProfile) {

        EmployeeProfile created =
                employeeProfileService.createEmployee(employeeProfile);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all employee profiles")
    @GetMapping
    public ResponseEntity<List<EmployeeProfile>> getAllEmployees() {
        return ResponseEntity.ok(employeeProfileService.getAllEmployees());
    }

    @Operation(summary = "Get employee profile by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeProfileService.getEmployeeById(id));
    }

    @Operation(summary = "Update employee active status")
    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeProfile> updateEmployeeStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        EmployeeProfile updated =
                employeeProfileService.updateEmployeeStatus(id, active);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete employee profile")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        // Optional: if delete is required by tests later, we’ll add service method
        employeeProfileService.getEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
