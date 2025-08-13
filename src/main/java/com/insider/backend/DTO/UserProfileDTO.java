package com.insider.backend.DTO;

public class UserProfileDTO {

    private String name;
    private String designation;      
    private Long sapid;
    private Long phoneNumber;
    private String project;
    private Integer total_appreciation;
    private Integer rank;

    public UserProfileDTO() {}

    public UserProfileDTO(String name, String designation, Long sapid,
                          Long phoneNumber, String project,
                          Integer total_appreciation, Integer rank) {
        this.name = name;
        this.designation = designation;
        this.sapid = sapid;
        this.phoneNumber = phoneNumber;
        this.project = project;
        this.total_appreciation = total_appreciation;
        this.rank = rank;
    }

    // getters/setters...
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public Long getSapid() { return sapid; }
    public void setSapid(Long sapid) { this.sapid = sapid; }

    public Long getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Long phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }

    public Integer getTotal_appreciation() { return total_appreciation; }
    public void setTotal_appreciation(Integer total_appreciation) { this.total_appreciation = total_appreciation; }

    public Integer getRank() { return rank; }
    public void setRank(Integer rank) { this.rank = rank; }
}
