package com.macm.cpdash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macm.cpdash.domain.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	public UserEntity findByEmail(String s);
}
