package com.macm.cpdash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macm.cpdash.domain.entities.SheetEntity;

/**
 * 
 * @author aeroui
 *
 */
public interface SheetRepository extends JpaRepository<SheetEntity, Long> {

}
