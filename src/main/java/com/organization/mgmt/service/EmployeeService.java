package com.organization.mgmt.service;

import com.organization.mgmt.entity.Task;
import com.organization.mgmt.repository.EmployeeRepository;
import com.organization.mgmt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.ReactiveSortHandlerMethodArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity<Object> findAllTasks(Integer page, Integer limit, Integer employeeId){

        Pageable pageable = (Pageable) PageRequest.of(page,limit);

        List<Task> tasklist = taskRepository.findAllByEmployeeId(employeeId,pageable);
        if (tasklist.size()>0){
            return ResponseEntity.ok(tasklist);
        }else {
            return ResponseEntity.noContent().build();
        }

    }




}
