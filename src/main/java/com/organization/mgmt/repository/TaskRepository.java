package com.organization.mgmt.repository;

import com.organization.mgmt.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findAllByEmployeeId(Integer employeeId, Pageable pageable);
}
