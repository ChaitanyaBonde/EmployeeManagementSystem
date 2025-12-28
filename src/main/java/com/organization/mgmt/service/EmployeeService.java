package com.organization.mgmt.service;

import com.organization.mgmt.component.JwtUtility;
import com.organization.mgmt.entity.Employee;
import com.organization.mgmt.entity.Task;
import com.organization.mgmt.repository.EmployeeRepository;
import com.organization.mgmt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.ReactiveSortHandlerMethodArgumentResolver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmployeeUserDetailsService employeeUserDetailsService;

    @Autowired
    private JwtUtility jwtUtility;

    public ResponseEntity<Object> login(Employee employee) {
        UserDetails userDetails = employeeUserDetailsService.loadUserByUsername(employee.getUsername());
        if (!encoder.matches(employee.getPassword(),userDetails.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Creditetials");
        }
        return ResponseEntity.ok(
                "{\n" +
                "  \"JWt Auth Token\": "+jwtUtility.genrateToken(userDetails)+"\n" +
                " }");
    }

    public ResponseEntity<Object> findAllTasks(Integer page, Integer limit, String employeeId){

        Pageable pageable = PageRequest.of(page,limit);

        List<Task> tasklist = taskRepository.findAllByEmployeeId(employeeId,pageable);
        if (tasklist.size()>0){
            return ResponseEntity.ok(tasklist);
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found");
        }

    }

    public ResponseEntity<Object> EditTask(Integer taskId, String Ststus){
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()){
            task.get().setStatus(Ststus);
            taskRepository.save(task.get());
            return ResponseEntity.ok("Task saved");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found");
    }

}
