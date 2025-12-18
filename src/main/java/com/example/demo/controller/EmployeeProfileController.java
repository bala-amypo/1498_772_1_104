package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;

@RestController
public class EmployeeProfileController {

    private final EmployeeProfileService es;

   
    public EmployeeProfileController(EmployeeProfileService es) {
        this.es = es;
    }

    
    @PostMapping("/postemployee")
    public EmployeeProfile postemployee(@RequestBody EmployeeProfile emp) {
        return es.createEmployee(emp);
    }

    
    @GetMapping("/getallemployees")
    public List<EmployeeProfile> getall() {
        return es.getAllEmployee();
    }

   
    @GetMapping("/getemployee/{id}")
    public Optional<EmployeeProfile> getbyid(@PathVariable Long id) {
        return es.getEmployeeById(id);
    }

    
    @PutMapping("/updateemployeestatus/{id}")
    public EmployeeProfile updateStatus(@PathVariable Long id,@RequestParam boolean active) {
        return es.UpdateEmployeeStatus(id, active);
    }

    
}
