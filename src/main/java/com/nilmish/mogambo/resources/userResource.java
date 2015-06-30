package com.nilmish.mogambo.resources;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.dao.*;
import com.nilmish.mogambo.entities.Following;
import com.nilmish.mogambo.entities.Tag;
import com.nilmish.mogambo.entities.User;
import com.nilmish.mogambo.feed.FeedGenerator;
import com.nilmish.mogambo.utils.GuiceInjector;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import io.dropwizard.auth.Auth;
import org.mongodb.morphia.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilesh.m on 15/06/15.
 */

@Path("/user")
@Api("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    public static final Logger logger = LoggerFactory.getLogger(UserResource.class);
    @Inject private UserDAO userDAO;
    @Inject private UserPostDAO userPostDAO;
    @Inject private RelationshipDAO relationshipDAO;
    @Inject private FollowingDAO followingDAO;
    @Inject private TagDAO tagDAO;
    @Inject private FeedGenerator feedGenerator;

    @Inject
    public UserResource() {
        GuiceInjector.getInjector().injectMembers(this);
    }


    // TODO : throw an exception if cannot save the user in DB like in socialcs code
    @Path("/saveUser")
    @ApiOperation(value = "Saves the New User", notes = "This API needs to be used on SignUp", response = Boolean.class)
    @POST
    public Boolean saveUser(User user){
        sanitiseUser(user);
        Key<User> key=userDAO.save(user);
        if(key==null || key.getId()==null){
            logger.info("could not save the user: "+user.toString());
            return false;
        }
        else {
            logger.info("user successfully saved in db: "+user.toString());
            return true;
        }
    }

    @Path("/getUser")
    @ApiOperation(value = "Get the User with given *username*", notes = "This API need to be used " +
            "when we need User object corresponding to *username*", response = User.class)
    @GET
    public User getUser(@Auth UserSession userSession, @QueryParam("username") String username){
        return userDAO.findUser(username);
    }

    @Path("/getAllUser")
    @ApiOperation(value = "Get All the users", notes = "This is utility API to get all the users from DB", response = User.class, responseContainer = "List")
    @GET
    public List<User> getAllUser(@Auth UserSession userSession){
        return userDAO.findAllUsers();
    }



    @Path("/followUser")
    @ApiOperation(value = "The authenticated user follows *followingUsername*", notes = "This API is used if authenticated" +
            "user wants to follow user *followingUsername*" , response = Boolean.class)
    @GET
    public Boolean followUser(@Auth UserSession userSession,@QueryParam("followingUsername") String followingUsername){
        String username=userSession.getUsername();
        if(username.equals(followingUsername)){
            return false;
        }
        relationshipDAO.follow(username, followingUsername, 1);
        followingDAO.addUserFollower(username, followingUsername);
        return true;
    }



    @Path("/unfollowUser")
    @ApiOperation(value = "The authenticated user unfollows *unfollowingUsername*", notes = "This API is used if authenticated" +
            "user wants to unfollow user *unfollowingUsername*" , response = Boolean.class)
    @GET
    public Boolean unfollowUser(@Auth UserSession userSession,@QueryParam("unfollowingUsername") String unfollowingUsername){
        String username=userSession.getUsername();
        if(username.equals(unfollowingUsername)){
            return false;
        }
        relationshipDAO.unfollow(username, unfollowingUsername);
        followingDAO.removeUserFollower(username, unfollowingUsername);
        return true;
    }



    @Path("/getFollowingUsers")
    @ApiOperation(value = "Returns the list of all the following users", notes = "This API returns the list" +
            "of all the following users" , response = String.class, responseContainer = "List")
    @GET
    public List<String> getFollowingUser(@Auth UserSession userSession){
        String username=userSession.getUsername();
        Following following=followingDAO.get(username);
        List<String> followingUsernameList=following.getFollowingUsernameList();
        if(followingUsernameList==null){
            followingUsernameList= Lists.newArrayList();
        }
        return followingUsernameList;
    }


    private void sanitiseUser(User user) {
        user.setEmailId(removeSpacesAndLowerCase(user.getEmailId()));
        user.setName(removeSpacesAndLowerCase(user.getName()));
        user.setUsername(removeSpacesAndLowerCase(user.getUsername()));
        user.setUserPhotoPath(removeSpacesAndLowerCase(user.getUserPhotoPath()));
    }

    private String removeSpacesAndLowerCase(String str) {
        return str.trim().toLowerCase();
    }
}
