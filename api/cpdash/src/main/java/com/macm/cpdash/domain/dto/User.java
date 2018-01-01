package com.macm.cpdash.domain.dto;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.macm.cpdash.domain.entities.AuthorityEntity;
import com.macm.cpdash.domain.entities.UserEntity;

/**
 * 
 * @author AEroui
 *
 */
public class User {

	private long id;
	private String username;
	private String password;
	private String email;
	private Date creationDate;
	private Date updateDate;
	private boolean enabled;
	private Date lastPasswordResetDate;
	private Set<AuthorityEntity> authorities;

	public User() {
		super();
	}

	public User(String username, String password, String email, Date creationDate, Date updateDate, Set<AuthorityEntity> authorities) {
		this();
		this.username = username;
		this.password = password;
		this.email = email;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.enabled = true;
		this.authorities = authorities;

	}

	public User(long id, String username, String password, String email, Date creationDate, Date updateDate, Set<AuthorityEntity> authorities) {
		this(username, password, email, creationDate, updateDate, authorities);
		this.id = id;
	}

	public User(UserEntity userEntity) {
		this(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail(),
				userEntity.getCreationDate(), userEntity.getUpdateDate(), userEntity.getAuthorities());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastPasswordResetDate() {
		return this.lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public Set<AuthorityEntity> getAuthorities() {
		if (this.authorities == null)
			return new TreeSet<>();

		return this.authorities;
	}

	public void setAuthorities(Set<AuthorityEntity> authorities) {
		this.authorities = authorities;
	}

}
