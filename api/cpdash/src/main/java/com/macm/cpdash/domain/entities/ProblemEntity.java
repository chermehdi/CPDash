package com.macm.cpdash.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author aeroui
 *
 */
@Entity
@Table(name = "problems")
public class ProblemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "problem_name")
	private String name;

	@Column(name = "problem_description")
	private String description;

	@Column(name = "problem_url")
	private String url;

	@Column(name = "problem_difficulty")
	private int difficulty;

	@Column(name = "problem_tags")
	private String tags;

	@ManyToOne
	@JoinColumn(name = "sheet_id")
	private SheetEntity sheet;

	public ProblemEntity() {
		super();
	}

	public ProblemEntity(String name, String description, String url, int difficulty, String tags, SheetEntity sheet) {
		this();
		this.name = name;
		this.description = description;
		this.url = url;
		this.difficulty = difficulty;
		this.tags = tags;
		this.sheet = sheet;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public SheetEntity getSheet() {
		return sheet;
	}

	public void setSheet(SheetEntity sheet) {
		this.sheet = sheet;
	}

}
