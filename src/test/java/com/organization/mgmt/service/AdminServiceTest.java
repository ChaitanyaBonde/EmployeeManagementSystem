package com.organization.mgmt.service;

import com.organization.mgmt.entity.Admin;
import com.organization.mgmt.repository.Adminrepository;
import com.organization.mgmt.repository.EmployeeRepository;
import com.organization.mgmt.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.crypto.Data;


import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private Adminrepository adminrepo;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService;

    Admin request = new Admin();
    Admin admin = new Admin();

    @BeforeEach
    void setUp() {
        admin.setName("testAdmin");
        admin.setUsername("tadmin");
        admin.setPassword("tpass");
        admin.setCreatedAt(new Timestamp(new Date().getTime()));
        request.setName("testAdmin");
        request.setUsername("tadmin");
        request.setPassword("tpass");
    }

    @Test
    void registerAdmiin() {
        when(adminrepo.save(request)).thenReturn(admin);
        ResponseEntity<Object> response = adminService.registerAdmiin(request);

        assertNotNull(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admin Registered", response.getBody());

        verify(adminrepo, times(1)).save(request);


    }

    @Test
    void login() {
    }

    @Test
    void addEmployee() {
    }

    @Test
    void getAllEmployees() {
    }

    @Test
    void getEmployeedetails() {
    }

    @Test
    void assginTask() {
    }
}