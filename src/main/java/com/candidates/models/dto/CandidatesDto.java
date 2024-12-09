package com.candidates.models.dto;

import java.util.Date;

/**
 * Clase DTO que representa a un candidato en la API.
 * Esta clase se utiliza para transferir datos entre la capa de presentación y la capa de servicio.
 * @author Bryan Núñez
 */
public class CandidatesDto {

    CandidatesDto(){
        //Constructor
    }
    
    public CandidatesDto(Long id, String name, String email,
			String gender, Long salaryExpected, Date creationDate, String userCreate 
			) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.salaryExpected = salaryExpected;
		this.creationDate = creationDate;
		this.userCreate = userCreate;
	}

    
    private Long id;
    private String name;
    private String email;
    private String gender;
    private Long salaryExpected;
    private Date creationDate;
    private String userCreate;
    private Date updateDate;
    private String userUpdate;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Long getSalaryExpected() {
        return salaryExpected;
    }
    public void setSalaryExpected(Long salaryExpected) {
        this.salaryExpected = salaryExpected;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public String getUserCreate() {
        return userCreate;
    }
    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
	public String getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

    
}
