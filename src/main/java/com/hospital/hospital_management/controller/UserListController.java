package com.hospital.hospital_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospital_management.bean.Login;
import com.hospital.hospital_management.service.IUserListService;

@RestController
@RequestMapping("/userList")
public class UserListController {

	@Autowired
	@Qualifier("userlist")
	private IUserListService loginService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/check")
	public boolean isUserExists(@RequestBody Login login) {
		return loginService.isUserExists(login);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		List<Login> results = loginService.getAllUsers();
		return ResponseEntity.ok(results);
	}
}
