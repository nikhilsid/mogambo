package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.dao.UserDAO;
import com.nilmish.mogambo.entities.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by nilesh.m on 15/06/15.
 */

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDAO userDAO;

    @Inject
    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Path("/save")
    @POST
    public boolean saveUser(){
        userDAO.save(new User("nilmish","nilesh mishra","nilmish.iit@gmail.com","path",20));
        userDAO.save(new User("nilmish1","nilesh mishra","nilmish.iit@gmail.com","path",20));
        return true;
    }

    @Path("/get")
    @GET
    public List<User> getUser(){
        return userDAO.findAllUsers();
    }
}
