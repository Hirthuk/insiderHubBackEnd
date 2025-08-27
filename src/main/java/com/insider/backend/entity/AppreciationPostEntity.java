package com.insider.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Appreciations", 
indexes = {
		@Index(name="Idx_from_sapid", columnList= "from_sapid"),
		@Index(name="Idx_to_sapid", columnList= "to_sapid")
}
)
public class AppreciationPostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message= "This field is mandatory")
	@Column(name = "from_sapid", nullable = false)
	private Long from_sapid;
	
	@NotNull(message= "This field is mandatory")
	@Column(name = "to_sapid", nullable = false)
	private Long to_sapid;
	
	@Column(name= "name", nullable=false)
	private String getterName;
	
	@NotNull(message= "This field is mandatory")
	@Column(name = "appreciation_message", nullable = false)
	private String appreciation_message;
	
	@Column(name = "likes")
	private Long likes = (long) 0;
	
	@Column(name = "creation_date")
	private LocalDateTime creation_date;
	
	@PrePersist
	protected void Oncreate() {
		this.creation_date = LocalDateTime.now();
	}
	
	public String getGetterName() {
		return getterName;
	}

	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFrom_sapid() {
		return from_sapid;
	}

	public void setFrom_sapid(Long from_sapid) {
		this.from_sapid = from_sapid;
	}

	public Long getTo_sapid() {
		return to_sapid;
	}

	public void setTo_sapid(Long to_sapid) {
		this.to_sapid = to_sapid;
	}

	public String getAppreciation_message() {
		return appreciation_message;
	}

	public void setAppreciation_message(String appreciation_message) {
		this.appreciation_message = appreciation_message;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public LocalDateTime getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(LocalDateTime creation_date) {
		this.creation_date = creation_date;
	}

	public AppreciationPostEntity(Long id, @NotNull(message = "This field is mandatory") Long from_sapid,
			@NotNull(message = "This field is mandatory") Long to_sapid,
			@NotNull(message = "This field is mandatory") String appreciation_message, Long likes,
			LocalDateTime creation_date) {
		super();
		this.id = id;
		this.from_sapid = from_sapid;
		this.to_sapid = to_sapid;
		this.appreciation_message = appreciation_message;
		this.likes = likes;
		this.creation_date = creation_date;
	}

	public AppreciationPostEntity(Long id, @NotNull(message = "This field is mandatory") Long from_sapid,
			@NotNull(message = "This field is mandatory") Long to_sapid,
			@NotNull(message = "This field is mandatory") String appreciation_message, LocalDateTime creation_date) {
		super();
		this.id = id;
		this.from_sapid = from_sapid;
		this.to_sapid = to_sapid;
		this.appreciation_message = appreciation_message;
		this.creation_date = creation_date;
	}

	public AppreciationPostEntity() {
	}

	public AppreciationPostEntity(Long id, @NotNull(message = "This field is mandatory") Long from_sapid,
			@NotNull(message = "This field is mandatory") Long to_sapid, String getterName,
			@NotNull(message = "This field is mandatory") String appreciation_message, Long likes,
			LocalDateTime creation_date) {
		super();
		this.id = id;
		this.from_sapid = from_sapid;
		this.to_sapid = to_sapid;
		this.getterName = getterName;
		this.appreciation_message = appreciation_message;
		this.likes = likes;
		this.creation_date = creation_date;
	}
	
	

}
