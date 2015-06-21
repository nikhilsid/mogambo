package com.nilmish.mogambo.auth;

/**
 * Created by nilesh.m on 20/06/15.
 */
public class AccessTokenCredentials {
    private final String token;

    public AccessTokenCredentials(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
