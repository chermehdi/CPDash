package com.macm.cpdash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macm.cpdash.domain.entities.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>{

}
