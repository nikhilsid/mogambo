package com.nilmish.mogambo.auth;

import javax.ws.rs.core.Response;

/**
 * Created by nilesh.m on 21/06/15.
 */
public class ApiException extends Exception {
    private final Response.Status status;
    private final String message;

    public ApiException(Response.Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response.Status getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }

}
