package com.macm.cpdash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macm.cpdash.domain.entities.ProblemEntity;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long>{
	
}
