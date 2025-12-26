package com.organization.mgmt.controller;

import com.organization.mgmt.entity.Admin;
import com.organization.mgmt.entity.Employee;
import com.organization.mgmt.service.AdminService;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody Admin admin){
        return adminService.registerAdmiin(admin);
    }

    @PostMapping("/add/employee")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee){
        return adminService.addEmployee(employee);
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<Object> getAllEmployee(@RequestParam Integer page,
                                                 @RequestParam Integer limit){
        return adminService.getAllEmployees(page,limit);
    }

    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<Object> getEmployee(@PathVariable String employeeId){
        return adminService.getEmployeedetails(employeeId);
    }
}
