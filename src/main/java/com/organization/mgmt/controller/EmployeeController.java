package com.organization.mgmt.controller;

import com.organization.mgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/GetAllTask/{employeeId}")
    public ResponseEntity<Object> getAllTask(@RequestParam Integer page,
                                             @RequestParam Integer limit,
                                             @PathVariable Integer employeeId){
        return employeeService.findAllTasks(page,limit, employeeId);
    }

    @PutMapping("/completeTask/{taskId}")
    public ResponseEntity<Object> editTask(@PathVariable Integer taskId){
        return employeeService.editTask(taskId);
    }

    @PutMapping("/editEmployeeDetails/{employeeId}")
    public ResponseEntity<Object> editEmployeeDetails(@PathVariable Integer employeeId){
        return employeeService.editEmployeedetails(employeeId);
    }
}
