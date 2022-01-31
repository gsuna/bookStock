package com.gsuna.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gsuna.project.enums.GenderType;

@Entity
public class Customer extends BaseEntity {
	
	@Column
	private Long identityNumber;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	
	@Column
	@Convert(converter = GenderType.Converter.class)
	private GenderType gender;
	
	public Long getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(Long identityNumber) {
		this.identityNumber = identityNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public GenderType getGender() {
		return gender;
	}
	public void setGender(GenderType gender) {
		this.gender = gender;
	}

}
