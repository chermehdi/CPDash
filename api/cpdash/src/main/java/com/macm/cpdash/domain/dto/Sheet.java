package com.macm.cpdash.domain.dto;

import com.macm.cpdash.domain.entities.SheetEntity;

/**
 * 
 * @author AEroui
 *
 */
public class Sheet {

	long id;
	String name;
	String description;
	String hash;

	public Sheet() {
		/* Default Constructor */
	}

	public Sheet(long id, String name, String description, String hash) {
		this.id = id;
		this.name = name;
		this.description = name;
		this.hash = hash;
	}

	public Sheet(SheetEntity sheetEntity) {
		this(sheetEntity.getId(), sheetEntity.getName(), sheetEntity.getDescription(), sheetEntity.getSheetHash());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
