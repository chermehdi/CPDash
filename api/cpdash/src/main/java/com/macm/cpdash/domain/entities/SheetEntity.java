package com.macm.cpdash.domain.entities;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.macm.cpdash.domain.dto.Problem;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @author aeroui
 */
@Entity
@Table(name = "sheets")
public class SheetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "sheet_name")
	private String name;

	@Column(name = "sheet_description")
	private String description;

	@Column(name = "sheet_hash")
	private String sheetHash;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = false)
	private UserEntity owner;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sheet_problem", joinColumns = @JoinColumn(name = "sheet_id"), inverseJoinColumns = @JoinColumn(name = "problem_id"))
	private Set<ProblemEntity> problems;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;

	public SheetEntity() {
		/* Default Constructor */
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getSheetHash() {
		return sheetHash;
	}

	public void setSheetHash(String sheetHash) {
		this.sheetHash = sheetHash;
	}

    public Set<ProblemEntity> getProblems() {
	    if(this.problems == null)
	        return new HashSet<>();

	    return this.problems;
    }

    public void setProblems(Set<ProblemEntity> problems) {
        this.problems = problems;
    }
}
