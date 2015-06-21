package com.nilmish.mogambo.auth;

import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nilesh.m on 20/06/15.
 */

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    private AccessTokenService accessTokenService;
    public static final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    @Inject
    public AuthResource(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @POST
    @Path("/login")
    public AccessTokenCredentials getEvent(UserDetails userDetails) throws ApiException {
        logger.info("login request from userId: " + userDetails.getUsername());
        boolean authenticated=true;
        if(authenticated){
            UserSession agent=accessTokenService.createAccessToken(new UserSession(userDetails.getUsername()));
            AccessTokenCredentials accessTokenCredentials=new AccessTokenCredentials(agent.getAccessToken());
            return accessTokenCredentials;
        }
        else{
            throw new ApiException(Response.Status.UNAUTHORIZED, "username and password did not match");
        }
    }

    @POST
    @Path("/logout")
    public boolean logout(@Auth UserSession userSession){
        accessTokenService.removeAccessToken(userSession.getAccessToken());
        logger.info("user with userId : "+ userSession.getUsername() + " is logged out");
        return true;
    }
}
