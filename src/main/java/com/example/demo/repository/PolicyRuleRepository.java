package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.PolicyRule;

public interface PolicyRuleRepository extends JpaRepository<PolicyRule, Long> {

    List<PolicyRule> findByActiveTrue();

    
}