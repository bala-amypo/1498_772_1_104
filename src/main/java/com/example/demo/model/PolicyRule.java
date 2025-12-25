package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "policy_rules")
public class PolicyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int maxPerEmployee;

    // Default constructor
    public PolicyRule() {}

    // Parametric constructor
    public PolicyRule(String name, String description, int maxPerEmployee) {
        this.name = name;
        this.description = description;
        this.maxPerEmployee = maxPerEmployee;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getMaxPerEmployee() { return maxPerEmployee; }
    public void setMaxPerEmployee(int maxPerEmployee) { this.maxPerEmployee = maxPerEmployee; }
}
