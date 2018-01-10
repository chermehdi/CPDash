package com.macm.cpdash.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final Long id;
	private final String username;
	private final String password;
	private final String email;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	private final Date lastPasswordResetDate;

	public JwtUser(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities, boolean enabled, Date lastPasswordResetDate) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return "JwtUser{" + "id=" + id + ", userName='" + username + '\'' + ", password='" + password + '\''
				+ ", email='" + email + '\'' + ", authorities=" + authorities + ", enabled=" + enabled
				+ ", lastPasswordResetDate=" + lastPasswordResetDate + '}';
	}

}
