package com.organization.mgmt.repository;

import com.organization.mgmt.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Adminrepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByUsername(String username);

}
