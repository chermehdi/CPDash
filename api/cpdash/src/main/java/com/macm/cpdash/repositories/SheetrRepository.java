package com.macm.cpdash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macm.cpdash.domain.entities.SheetEntity;

public interface SheetrRepository extends JpaRepository<SheetEntity, Long>{
	
}
