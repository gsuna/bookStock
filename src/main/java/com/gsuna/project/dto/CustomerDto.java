package com.gsuna.project.dto;

import java.util.Date;

import com.gsuna.project.enums.GenderType;

public class CustomerDto extends BaseEntityDto {
	
	private Long identityNumber;
	
	private String name;
	
	private String surname;
	
	private Date birthDate;
	
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
