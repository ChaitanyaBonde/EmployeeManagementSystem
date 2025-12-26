package com.organization.mgmt.repository;

import com.organization.mgmt.entity.Employee;
import org.hibernate.annotations.Struct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmployeeId(String employeeid);

//    List<Employee> findAll(Pageable pageable);


}
