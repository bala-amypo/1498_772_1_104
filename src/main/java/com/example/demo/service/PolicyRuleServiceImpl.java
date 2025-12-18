package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.PolicyRule;
import com.example.demo.repository.PolicyRuleRepository;

@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {

    private final PolicyRuleRepository repo;

    public PolicyRuleServiceImpl(PolicyRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public PolicyRule createRule(PolicyRule rule) {
        rule.setActive(true);
        return repo.save(rule);
    }

    @Override
    public List<PolicyRule> getAllRules() {
        return repo.findAll();
    }

    @Override
    public List<PolicyRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}