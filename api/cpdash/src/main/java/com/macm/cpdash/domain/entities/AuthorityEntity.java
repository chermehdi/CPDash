package com.macm.cpdash.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AUTHORITY")
public class AuthorityEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", length = 50, unique = true)
	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthorityName name;

	public AuthorityEntity() {
	}

	public AuthorityEntity(AuthorityName name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}
}
