package com.insider.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
@Entity
@Table(name = "userRequest",
indexes = {
		@Index(name = "Idx_request1_sapid", columnList = "sapid"),
		@Index(name="Idx_reuqest1_email", columnList = "email"),
		@Index(name="Idx_request1_phoneNumber", columnList = "phone_number")
})
public class UserRequestEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Name is mandatory")
	@Column(name="name", nullable=false)
	private String name;
	
	@Email(message="Not a valid email")
	@NotNull(message="Email cannot be Null")
	@Column(name="email", nullable=false,unique=true)
	private String email;
	
	@NotNull(message="Phone number is required")
	@Digits(integer = 10, fraction = 0, message = "Phone number must be 10 digits")
	@Column(name="phone_number", nullable=false, unique=true)
	private Long phone_number;

	
	@NotNull(message= "Sapid is mandatory")
	@Column(name="sapid",nullable=false,unique=true)
	private Long sapid;
	
	@NotNull(message= "designation is mandatory")
	@Column(name="designation", nullable=false)
	private String designation;
	
	@NotNull(message= "password is mandatory")
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name = "creation_date", updatable = false)
	private LocalDateTime creationDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	protected void Oncreate() {
		this.creationDate = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
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

	public String getDesignation() {
	    return designation;
	}

	public void setDesignation(String designation) {
	    this.designation = designation;
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
	
	
	@PrePersist
	protected void onCreate() {
	    this.creationDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
	    this.updateDate = LocalDateTime.now();
	}

	
	public UserRequestEntity(){
		
	}

	public UserRequestEntity(Long id, String name,
			 String email,
		     Long phone_number,
		     Long sapid,
			 String designation,
			String password, 
			LocalDateTime creationDate,
			LocalDateTime updateDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.sapid = sapid;
		this.designation = designation;
		this.password = password;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	public UserRequestEntity(Long id, String name,
			 String email,
		     Long phone_number,
		     Long sapid,
			 String designation,
			LocalDateTime creationDate,
			LocalDateTime updateDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.sapid = sapid;
		this.designation = designation;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}
	
	
	
	

}
