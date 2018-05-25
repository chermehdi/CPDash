package com.macm.cpdash.domain.dto;

import com.macm.cpdash.domain.entities.ProblemEntity;

/**
 * 
 * @author aeroui
 *
 */
public class Problem {

	private Long id;

	private String name;

	private String description;

	private String url;

	private Integer difficulty;

	private String tags;

	public Problem() {
		super();
	}

	public Problem(Long id, String name, String description, String url, int difficulty, String tags) {
		this();
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
		this.difficulty = difficulty;
		this.tags = tags;
	}

	public Problem(ProblemEntity problemEntity) {
		this(problemEntity.getId(), problemEntity.getName(), problemEntity.getDescription(), problemEntity.getUrl(),
				problemEntity.getDifficulty(), problemEntity.getTags());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTags() {
		return tags;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


}
