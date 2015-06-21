package com.nilmish.mogambo.auth;

import com.google.common.collect.Maps;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Map;

/**
 * Created by nilesh.m on 21/06/15.
 */
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {
    @Override
    public Response toResponse(ApiException exception) {
        Map<String, Object> response = Maps.newHashMap();
        response.put("status", exception.getStatus().getStatusCode());
        response.put("message", exception.getMessage());
        return Response.status(exception.getStatus().getStatusCode()).entity(response).type(MediaType.APPLICATION_JSON_TYPE).build();

    }
}
