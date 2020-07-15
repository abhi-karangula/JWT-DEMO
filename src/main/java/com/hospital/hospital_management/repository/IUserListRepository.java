package com.hospital.hospital_management.repository;

import java.util.List;

import com.hospital.hospital_management.bean.Login;

public interface IUserListRepository {

	public boolean isUserExists(Login login);
	
	public Login loadUsersByUsername(String userName);
	
	public Login save(Login login);
	
	public List<Login> getAllUsers();
}
