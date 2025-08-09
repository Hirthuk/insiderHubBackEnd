package com.insider.backend.DTO;

public class LoginUserRequest {

    private Long sapid;
    private String password;

    public Long getSapid() {
        return sapid;
    }

    public void setSapid(Long sapid) {
        this.sapid = sapid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginUserRequest(Long sapid, String password) {
        this.sapid = sapid;
        this.password = password;
    }

    public LoginUserRequest() {
    }
}
