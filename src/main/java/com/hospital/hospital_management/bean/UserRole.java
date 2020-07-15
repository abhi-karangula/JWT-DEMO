package com.hospital.hospital_management.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_role")
public class UserRole {

	private int id;
	private String role;
	private Login login;

	@Id
	@GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sequence", sequenceName = "user_role_seq", allocationSize = 1, initialValue = 1)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonIgnore
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
