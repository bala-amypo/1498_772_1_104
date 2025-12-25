package com.example.demo.controller;

import com.example.demo.model.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/policy-rules")
@Tag(name = "Policy Rules Endpoints")
public class PolicyRuleController {

    private final PolicyRuleService policyRuleService;

    public PolicyRuleController(PolicyRuleService policyRuleService) {
        this.policyRuleService = policyRuleService;
    }

    @PostMapping
    @Operation(summary = "Create a policy rule")
    public ResponseEntity<PolicyRule> createRule(@RequestBody PolicyRule rule) {
        return ResponseEntity.ok(policyRuleService.createRule(rule));
    }

    @GetMapping
    @Operation(summary = "List all policy rules")
    public ResponseEntity<List<PolicyRule>> getAllRules() {
        return ResponseEntity.ok(policyRuleService.getAllRules());
    }

    @GetMapping("/active")
    @Operation(summary = "List active policy rules")
    public ResponseEntity<List<PolicyRule>> getActiveRules() {
        return ResponseEntity.ok(policyRuleService.getActiveRules());
    }

    @PutMapping("/{id}/active")
    @Operation(summary = "Update active status of a policy rule")
    public ResponseEntity<PolicyRule> updateActiveStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(policyRuleService.updateActiveStatus(id, active));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a policy rule")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        policyRuleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}
