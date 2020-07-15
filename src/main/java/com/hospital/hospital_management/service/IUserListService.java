package com.hospital.hospital_management.service;

import java.util.List;

import com.hospital.hospital_management.bean.Login;

public interface IUserListService {
	public boolean isUserExists(Login login);
	
	public List<Login> getAllUsers();
}
