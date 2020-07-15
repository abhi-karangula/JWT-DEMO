package com.hospital.hospital_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.hospital_management.bean.Login;
import com.hospital.hospital_management.bean.UserRole;
import com.hospital.hospital_management.model.Role;
import com.hospital.hospital_management.model.UserDTO;
import com.hospital.hospital_management.repository.IUserListRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private IUserListRepository loginRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login = loginRepository.loadUsersByUsername(username);
		if (login != null) {
			List<GrantedAuthority> grantedAuthorities = login.getRoles().stream().map(r -> {
				return new SimpleGrantedAuthority(r.getRole());
			}).collect(Collectors.toList());
			return new User(login.getUserName(), login.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	@Transactional
	public boolean save(UserDTO user) {
		Login newUser = new Login();
		newUser.setUserName(user.getUserName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setMobileNo(user.getMobileNo());
		newUser.setName(user.getName());
		newUser.setAddress(user.getAddress());
		List<UserRole> roles = new ArrayList<>();
		for (Role role : user.getRoles()) {
			UserRole usrRole = new UserRole();
			usrRole.setRole(role.getRole());
			usrRole.setLogin(newUser);
			roles.add(usrRole);
		}
		newUser.setRoles(roles);
		Login response = loginRepository.save(newUser);
		return response != null;
	}
}
