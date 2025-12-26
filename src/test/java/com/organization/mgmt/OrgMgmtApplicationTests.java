package com.organization.mgmt;

import com.organization.mgmt.entity.Admin;
import com.organization.mgmt.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;

@SpringBootTest
class OrgMgmtApplicationTests {

	@Autowired
    AdminService adminService;



}
