package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.dao.FollowDAO;
import com.nilmish.mogambo.dao.UserDAO;
import com.nilmish.mogambo.dao.UserPostDAO;
import com.nilmish.mogambo.entities.User;
import com.nilmish.mogambo.entities.UserPost;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by nilesh.m on 15/06/15.
 */

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDAO userDAO;
    private UserPostDAO userPostDAO;
    private FollowDAO followDAO;

    @Inject
    public UserResource(UserDAO userDAO, UserPostDAO userPostDAO, FollowDAO followDAO) {
        this.userDAO = userDAO;
        this.userPostDAO = userPostDAO;
        this.followDAO = followDAO;
    }

    @Path("/save")
    @POST
    public boolean saveUser(User user){
        userDAO.save(user);
        return true;
    }

    @Path("/getAllUser")
    @GET
    public List<User> getAllUser(){
        return userDAO.findAllUsers();
    }

    @Path("/getUser")
    @GET
    public User getUser(@QueryParam("username") String username){
        return userDAO.get(username);
    }

    @Path("/savePost")
    @POST
    public boolean savePost(){
        userPostDAO.save(new UserPost());
        return true;
    }

//    @Path("/getFeed")
//    @GET
//    public List<UserPost> getFeed(@QueryParam("username") String username){
//
//    }

    @Path("/getFollowing")
    @GET
    public List<String> getFollowing(@QueryParam("username") String username){
        return followDAO.getFollowingUsernameList(username);
    }

}
