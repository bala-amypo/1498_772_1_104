package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;

@RestController
@RequestMapping("/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeProfile addEmployee(@RequestBody EmployeeProfile employee){
        return service.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public EmployeeProfile getEmployee(@PathVariable Long id){
        return service.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeProfile> getAllEmployees(){
        return service.getAllEmployees();
    }

    @PutMapping("/{id}")
    public EmployeeProfile updateEmployee(@PathVariable Long id, @RequestBody EmployeeProfile employee){
        return service.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        service.deleteEmployee(id);
        return "Deleted Successfully";
    }
}
