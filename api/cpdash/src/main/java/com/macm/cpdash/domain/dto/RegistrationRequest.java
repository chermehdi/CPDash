package com.macm.cpdash.domain.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationRequest {

	@NotEmpty
	@NotNull
	private String username;

	@NotEmpty
	@NotNull
	@Email
	private String email;

	@NotEmpty
	@NotNull
	private String password;

	@NotEmpty
	@NotNull
	private String passwordConfirmation;

	public RegistrationRequest() {
		super();
	}

	public RegistrationRequest(String username, String email, String password, String passwordConf) {
		this();
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConf;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConf) {
		this.passwordConfirmation = passwordConf;
	}

}
