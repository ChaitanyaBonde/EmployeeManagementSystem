package com.organization.mgmt.service;

import com.organization.mgmt.component.JwtUtility;
import com.organization.mgmt.entity.Admin;
import com.organization.mgmt.entity.Employee;
import com.organization.mgmt.entity.Task;
import com.organization.mgmt.repository.Adminrepository;
import com.organization.mgmt.repository.EmployeeRepository;
import com.organization.mgmt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


@Service
public class AdminService {
    @Autowired
    private Adminrepository adminrepo;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AdminUserDetailsService adminUserDetailsService;

    public ResponseEntity<Object> registerAdmiin(Admin admin){
        Optional<Admin> existingAdmin = adminrepo.findByUsername(admin.getUsername());
        if (existingAdmin.isPresent()){
            return ResponseEntity.badRequest().body("Username Already exist");
        }else {
            admin.setCreatedAt(new Timestamp(new Date().getTime()));
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            Admin save = adminrepo.save(admin);

            return ResponseEntity.ok("Admin Registered");
        }
    }

    public ResponseEntity<Object> login(Admin admin){
        UserDetails user = adminUserDetailsService.loadUserByUsername(admin.getUsername());
        if(!passwordEncoder.matches(admin.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credientials");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                "{\n" +
                "  \"JWt Auth Token\": "+jwtUtility.genrateToken(user)+"\n" +
                " }");
    }

    public ResponseEntity<Object> addEmployee(Employee employee){
        Optional<Employee> existingEmployee = employeeRepository.findByEmployeeId(employee.getEmployeeId()).isPresent()
                ? employeeRepository.findByEmployeeId(employee.getEmployeeId())
                : employeeRepository.findByUsername(employee.getUsername());
        if (existingEmployee.isEmpty()){
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employee.setRole("Employee");
            employeeRepository.save(employee);
            return ResponseEntity.ok("Employee Registered Registered");
        }else {
            return ResponseEntity.badRequest().body("Employee with "+employee.getEmployeeId()+" Id Or "+employee.getUsername()+" UserName Already exist");
        }
    }

    public ResponseEntity<Object> getAllEmployees(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page, limit);

        Page<Employee> employeeList = employeeRepository.findAll(pageable);

        if (!employeeList.isEmpty()){
            return ResponseEntity.ok(employeeList);
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Data Found");
        }
    }

    public ResponseEntity<Object>  getEmployeedetails(String employeeId){
        Optional<Employee> employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee.isPresent()){
            return ResponseEntity.ok(employee);
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Data Found");
        }
    }

    public ResponseEntity<Object> assginTask(Task task) {
        taskRepository.save(task);
        return ResponseEntity.ok("Task Assgined");
    }
}
