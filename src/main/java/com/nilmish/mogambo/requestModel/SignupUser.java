package com.nilmish.mogambo.requestModel;

/**
 * Created by nilesh.m on 04/07/15.
 */
public class SignupUser {
    private String username;
    private String name;
    private String emailId;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
