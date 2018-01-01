package com.macm.cpdash.domain.dto;

public class TokenResponse {

	private String token;
	private User user;

	public TokenResponse() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TokenResponse(String token, User user) {
		this.token = token;
		this.user = user;
	}

	@Override
	public String toString() {
		return "TokenResponse{" + "token='" + token + '\'' + '}';
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
