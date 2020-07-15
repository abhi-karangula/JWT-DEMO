package com.hospital.hospital_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.hospital_management.bean.Login;
import com.hospital.hospital_management.repository.IUserListRepository;

@Service("userList")
@Transactional
public class UserListServiceImpl implements IUserListService {

	@Autowired
	private IUserListRepository loginRepository;

	@Override
	public boolean isUserExists(Login login) {
		return loginRepository.isUserExists(login);
	}

	@Override
	public List<Login> getAllUsers() {
		return loginRepository.getAllUsers();
	}

}
