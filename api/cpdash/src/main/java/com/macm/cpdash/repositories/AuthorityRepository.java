package com.macm.cpdash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macm.cpdash.domain.entities.AuthorityEntity;
import com.macm.cpdash.domain.entities.AuthorityName;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

	public AuthorityEntity findByName(AuthorityName authorityName);

}
