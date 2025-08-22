package com.insider.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Index;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
@Entity
@Table(name = "userRequest",
indexes = {
		@Index(name = "Idx_sapid", columnList = "sapid"),
		@Index(name="Idx_email", columnList = "email"),
		@Index(name="Idx_phoneNumber", columnList = "phone_number")
})
public class UserRequestEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Name is mandatory")
	@Column(name="full_name", nullable=false)
	private String full_name;
	
	@Email(message="Not a valid email")
	@NotNull(message="Email cannot be Null")
	@Column(name="email", nullable=false,unique=true)
	private String email;
	
	@Column(name="phone_number", nullable=false,unique=true)
	@NotNull(message="Phone number is required")
	@Size(min=2, max=10, message="Number should be in 10 digit")
	private Long phone_number;
	
	@NotNull(message= "Sapid is mandatory")
	@Column(name="sapid",nullable=false,unique=true)
	private Long sapid;
	
	@NotNull(message= "desigination is mandatory")
	@Column(name="desigination", nullable=false)
	private String desigination;
	
	@NotNull(message= "password is mandatory")
	@Column(name="password", nullable=false)
	private String password;
	
	private LocalDateTime creationDate;
	
	protected void Oncreate() {
		this.creationDate = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(Long phone_number) {
		this.phone_number = phone_number;
	}

	public Long getSapid() {
		return sapid;
	}

	public void setSapid(Long sapid) {
		this.sapid = sapid;
	}

	public String getDesigination() {
		return desigination;
	}

	public void setDesigination(String desigination) {
		this.desigination = desigination;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	public UserRequestEntity(){
		
	}

	public UserRequestEntity(Long id, String full_name,
			 String email,
		     Long phone_number,
		     Long sapid,
			 String desigination,
			String password, 
			LocalDateTime creationDate) {
		super();
		this.id = id;
		this.full_name = full_name;
		this.email = email;
		this.phone_number = phone_number;
		this.sapid = sapid;
		this.desigination = desigination;
		this.password = password;
		this.creationDate = creationDate;
	}
	
	

}
