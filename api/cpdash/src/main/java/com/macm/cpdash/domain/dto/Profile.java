package com.macm.cpdash.domain.dto;

import java.util.Date;

import com.macm.cpdash.domain.entities.ProfileEntity;

public class Profile {

	private String firstName;

	private String lastName;

	private Date birthDate;

	private String picture;

	private String country;

	private String city;

	private Date creationDate;

	private Date updateDate;

	public Profile() {
		/* Default Constructor */
	}

	public Profile(String firstName, String lastName, Date birthDate, String picture, String country, String city,
			Date creationDate, Date updateDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.picture = picture;
		this.country = country;
		this.city = city;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	public Profile(ProfileEntity profileEntity) {
		this(profileEntity.getFirstName(), profileEntity.getLastName(), profileEntity.getBirthDate(),
				profileEntity.getPicture(), profileEntity.getCity(), profileEntity.getCountry(),
				profileEntity.getCreationDate(), profileEntity.getUpdateDate());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

}
