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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users",
    indexes = {
        @Index(name = "Idx_sapid", columnList = "sapid"),
        @Index(name = "idx_email", columnList = "email")
    }
)
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email is not Valid")
    @NotNull(message = "Email cannot be Null")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "Password field is mandatory")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "USER|ADMIN", message = "Role must be USER or ADMIN")  // Added for restriction
    @Column(name = "role", nullable = false)
    private String role = "USER";

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must have more than 2 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "SapId is required")
    @Column(name = "sapid", nullable = false, unique = true)
    private Long sapid;

    @NotBlank(message = "Designation is required")
    @Size(min = 2, max = 100, message = "Designation must have more than 2 characters")
    @Column(name = "designation", nullable = false)  // Fixed typo
    private String designation;  // Fixed name

    @Column(name = "phone_number")  
    private Long phoneNumber;

    @Column(name = "project_name")
    private String project_name;

    @Column(name = "total_appreciation")
    private Integer total_appreciation;

    @Column(name = "rank")
    private Integer rank = 0;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creation_date;  // Switched to LocalDateTime

    // Auto-set creation_date on save
    @PrePersist
    protected void onCreate() {
        this.creation_date = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {  // Fixed camelCase
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSapid() {
        return sapid;
    }

    public void setSapid(Long sapid) {
        this.sapid = sapid;
    }

    public String getDesignation() {  // Fixed
        return designation;
    }

    public void setDesignation(String designation) {  // Fixed
        this.designation = designation;
    }

    public Long getPhonenumber() {
        return phoneNumber;
    }

    public void setPhone_number(Long phoneNumber) {
       this.phoneNumber  = phoneNumber;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Integer getTotal_appreciation() {
        return total_appreciation;
    }

    public void setTotal_appreciation(Integer total_appreciation) {
        this.total_appreciation = total_appreciation;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    // Constructors
    public UsersEntity() {
       
    }

    // Constructor with fields (annotations removed from params; they belong on fields)
    public UsersEntity(String email, String password, String role, String name, Long sapid, String designation,
                       Long phoneNumber, String project_name, Integer total_appreciation, Integer rank, LocalDateTime creation_date) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.sapid = sapid;
        this.designation = designation;
        this.phoneNumber = phoneNumber;
        this.project_name = project_name;
        this.total_appreciation = total_appreciation;
        this.rank = rank;
        this.creation_date = creation_date;
    }
}
