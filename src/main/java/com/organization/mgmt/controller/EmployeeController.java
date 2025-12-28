package com.organization.mgmt.controller;

import com.organization.mgmt.entity.Employee;
import com.organization.mgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public ResponseEntity<Object> employeeLogin(@RequestBody Employee employee){
        return employeeService.login(employee);
    }

    @GetMapping("/GetAllTask/{employeeId}")
    public ResponseEntity<Object> getAllTask(@RequestParam Integer page,
                                             @RequestParam Integer limit,
                                             @PathVariable String employeeId){
        return employeeService.findAllTasks(page,limit, employeeId);
    }

    @PutMapping("/completeTask/{taskId}")
    public ResponseEntity<Object> EditTask(@PathVariable Integer taskId,@RequestParam String Status){
        return employeeService.EditTask(taskId,Status);
    }
}
