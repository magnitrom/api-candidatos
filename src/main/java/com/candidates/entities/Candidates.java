package com.candidates.entities;

import java.io.Serializable;
import java.util.Date;
import com.candidates.models.dto.CandidatesDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

/**
 * Esta clase es homologa a la tabla 'candidates' de la base de datos y contiene los atributos de un candidato.
 * 
 * @Entity: Indica que esta clase es una entidad de JPA.
 * @Table: Especifica el nombre de la tabla en la base de datos.
 * @Id: Marca el atributo como la clave primaria.
 * @GeneratedValue: Define la estrategia de generación del identificador.
 * @author Bryan Núñez
 */
@Entity
@Table(name = "candidates", catalog = "reto")
public class Candidates implements Serializable {

    private static final long serialVersionUID = 1L;

    public Candidates() {
        super();
    }

    public Candidates(Long id, String name, String email,
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

	public Candidates(CandidatesDto candidates){
         this.id = candidates.getId();
         this.name = candidates.getName();
         this.email = candidates.getEmail();
         this.gender = candidates.getGender();
         this.salaryExpected = candidates.getSalaryExpected();
         this.creationDate = candidates.getCreationDate();
         this.userCreate = candidates.getUserCreate();
         this.updateDate = candidates.getUpdateDate();
         this.userUpdate = candidates.getUserUpdate();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "gender", nullable = false, length = 20)
    private String gender;

    @Column(name = "salary_expected", nullable = false)
    private Long salaryExpected;

    @Column(name = "creation_date", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date creationDate;

    @Column(name = "user_create", nullable = false, length = 45)
    private String userCreate;

    @Column(name = "update_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date updateDate;

    @Column(name = "user_update", length = 45)
    private String userUpdate;

    public static long getSerialversionuid() {
        return serialVersionUID;
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
