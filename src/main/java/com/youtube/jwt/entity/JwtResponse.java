package com.youtube.jwt.entity;

public class JwtResponse {
    private Boolean success;
    private User user;
    private String jwtToken;

    public JwtResponse(User user, String jwtToken,Boolean success) {
        this.success=success;
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }


}
