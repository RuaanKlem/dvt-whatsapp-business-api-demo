package com.dvt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {
    private String token;
    private String expires_after;

    public Users() {
    }

    public Users(String token, String expires_after) {
        this.token = token;
        this.expires_after = expires_after;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires_after() {
        return expires_after;
    }

    public void setExpires_after(String expires_after) {
        this.expires_after = expires_after;
    }
}
