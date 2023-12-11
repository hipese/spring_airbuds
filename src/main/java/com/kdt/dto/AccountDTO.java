package com.kdt.dto;

import java.time.Instant;

public class AccountDTO {
	// pw 없음
	
	private String id;
	private String name;
	private Instant birth;
	private String contact;
	private String email;
	private String profile_image;
	private String background_image;
	private String role;
	private boolean enabled;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Instant getBirth() {
		return birth;
	}
	public void setBirth(Instant birth) {
		this.birth = birth;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public AccountDTO(String id, String name, Instant birth, String contact, String email, String profile_image,
			String background_image, String role, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.contact = contact;
		this.email = email;
		this.profile_image = profile_image;
		this.background_image = background_image;
		this.role = role;
		this.enabled = enabled;
	}
	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
