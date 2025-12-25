package com.example.demo.service;

import com.example.demo.model.PolicyRule;
import java.util.List;

public interface PolicyRuleService {
    PolicyRule createRule(PolicyRule rule);
    PolicyRule updateRuleStatus(Long id, boolean active);
    List<PolicyRule> getAllRules();
    List<PolicyRule> getActiveRules();
    void deleteRule(Long id);
}
