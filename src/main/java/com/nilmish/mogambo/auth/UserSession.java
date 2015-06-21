package com.nilmish.mogambo.auth;

/**
 * Created by nilesh.m on 20/06/15.
 */
public class UserSession {
    private String accessToken;
    private String username;

    public String getAccessToken() {
        return accessToken;
    }

    public String getUsername() {
        return username;
    }

    public UserSession(String username) {
        this.username = username;
    }

    public UserSession() {
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
