package com.kdt.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Register {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="birth")
	private Timestamp birth;
	
	@Column(name="email")
	private String email;
	
	@Column(name="profile_image")
	private String profile_image;
	
	@Column(name="background_image")
	private String background_image;
	
	@Column(name="role")
	private String role;
	
	@Column(name="enabled")
	private Boolean enabled;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getBirth() {
		return birth;
	}

	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getBackground_image() {
		return background_image;
	}

	public void setBackground_image(String background_image) {
		this.background_image = background_image;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Register(String id, String password, String name, Timestamp birth, String email, String profile_image,
			String background_image, String role, Boolean enabled) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.profile_image = profile_image;
		this.background_image = background_image;
		this.role = role;
		this.enabled = enabled;
	}

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
