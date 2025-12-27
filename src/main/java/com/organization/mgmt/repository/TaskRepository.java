package com.organization.mgmt.repository;

import com.organization.mgmt.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findAllByEmployeeId(String employeeId, Pageable pageable);
}
