package com.nilmish.mogambo.auth;

import com.google.inject.Inject;
import com.nilmish.mogambo.dao.UserDAO;
import com.nilmish.mogambo.entities.User;
import com.nilmish.mogambo.utils.GuiceInjector;
import com.wordnik.swagger.annotations.ApiOperation;
import io.dropwizard.auth.Auth;
import org.mongodb.morphia.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nilesh.m on 20/06/15.
 */

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject private AccessTokenService accessTokenService;
    @Inject private UserDAO userDAO;
    public static final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    @Inject
    public AuthResource() {
        GuiceInjector.getInjector().injectMembers(this);
    }

    @POST
    @Path("/login")
    public AccessTokenCredentials getEvent(UserDetails userDetails) throws ApiException {
        logger.info("login request from userId: " + userDetails.getUsername());
        boolean authenticated=true;
        User user=userDAO.get(userDetails.getUsername());
        if(user==null || user.getHashPassword()!=userDetails.getPassword()){
            authenticated=false;
        }
        if(authenticated){
            return getAccessTokenCredentials(userDetails.getUsername());
        }
        else{
            throw new ApiException(Response.Status.UNAUTHORIZED, "username and password did not match");
        }
    }


    @POST
    @ApiOperation(value = "Saves the New User", notes = "This API needs to be used on SignUp", response = AccessTokenCredentials.class)
    @Path("/signup")
    public AccessTokenCredentials signUpNewUser(@FormParam("username")String username, @FormParam("name") String name,
                                 @FormParam("emailId") String emailId, @FormParam("password") String password) throws ApiException {
        username=sanitise(username);
        name=sanitise(name);
        emailId=sanitise(emailId);
        User user=new User(username,name,emailId,password);
        Key<User> key=userDAO.save(user);
        if(key==null || key.getId()==null){
            logger.error("user with username: "+username+" name: "+name+" emailId: "+emailId+" could not be saved");
            throw new ApiException(Response.Status.UNAUTHORIZED, "could not save user into DB");
        }
        logger.info("user with username: "+username+" name: "+name+" emailId: "+emailId+" is signed Up and saved");
        AccessTokenCredentials accessTokenCredentials=getAccessTokenCredentials(username);
        return accessTokenCredentials;

    }

    private String sanitise(String username) {
        return username.trim().toLowerCase();
    }

    @POST
    @Path("/logout")
    public boolean logout(@Auth UserSession userSession){
        accessTokenService.removeAccessToken(userSession.getAccessToken());
        logger.info("user with userId : "+ userSession.getUsername() + " is logged out");
        return true;
    }


    private AccessTokenCredentials getAccessTokenCredentials(String username) {
        UserSession agent=accessTokenService.createAccessToken(new UserSession(username));
        AccessTokenCredentials accessTokenCredentials=new AccessTokenCredentials(agent.getAccessToken());
        return accessTokenCredentials;
    }
}
