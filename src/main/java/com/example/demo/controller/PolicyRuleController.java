package com.example.demo.controller;

import com.example.demo.model.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policy-rules")
@Tag(name = "Policy Rules Endpoints")
public class PolicyRuleController {

    @Autowired
    private PolicyRuleService policyRuleService;

    @Operation(summary = "Create a new policy rule")
    @PostMapping
    public ResponseEntity<PolicyRule> createRule(
            @Valid @RequestBody PolicyRule rule
    ) {
        return new ResponseEntity<>(
                policyRuleService.createRule(rule),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Get all policy rules")
    @GetMapping
    public ResponseEntity<List<PolicyRule>> getAllRules() {
        return ResponseEntity.ok(policyRuleService.getAllRules());
    }

    @Operation(summary = "Get active policy rules")
    @GetMapping("/active")
    public ResponseEntity<List<PolicyRule>> getActiveRules() {
        return ResponseEntity.ok(policyRuleService.getActiveRules());
    }

    @Operation(summary = "Update policy rule active status")
    @PutMapping("/{id}/active")
    public ResponseEntity<PolicyRule> updateActiveStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return ResponseEntity.ok(
                policyRuleService.updateActiveStatus(id, active)
        );
    }

    @Operation(summary = "Delete policy rule")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(
            @PathVariable Long id
    ) {
        policyRuleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}
