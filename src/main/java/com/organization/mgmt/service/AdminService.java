package com.organization.mgmt.service;

import com.organization.mgmt.entity.Admin;
import com.organization.mgmt.entity.Employee;
import com.organization.mgmt.repository.Adminrepository;
import com.organization.mgmt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService {
    @Autowired
    private Adminrepository adminrepo;

    @Autowired
    private EmployeeRepository employeeRepository;
    public ResponseEntity<Object> registerAdmiin(Admin admin){
        Optional<Admin> existingAdmin = adminrepo.findByUsername(admin.getUsername());
        if (existingAdmin.isPresent()){
            return ResponseEntity.badRequest().body("Username Already exist");
        }else {
            Admin save = adminrepo.save(admin);
            return ResponseEntity.ok("Admin Registered");
        }
    }

    public ResponseEntity<Object> addEmployee(Employee employee){
        Optional<Employee> existingEmployee = employeeRepository.findByEmployeeId(employee.getEmployeeId());
        if (existingEmployee.isEmpty()){
            employeeRepository.save(employee);
            return ResponseEntity.ok("Employee Registered Registered");
        }else {
            return ResponseEntity.badRequest().body("Employee with "+employee.getEmployeeId()+" Id Already exist");
        }
    }

    public ResponseEntity<Object> getAllEmployees(Integer page, Integer limit){
        Pageable pageable = (Pageable) PageRequest.of(page,limit);

        List<Employee> employeeList = employeeRepository.findAll((Sort) pageable);

        if (employeeList.size()>0){
            return ResponseEntity.ok(employeeList);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<Object>  getEmployeedetails(String employeeId){
        Optional<Employee> employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee.isPresent()){
            return ResponseEntity.ok(employee);
        }else {
            return ResponseEntity.noContent().build();
        }
    }
}
