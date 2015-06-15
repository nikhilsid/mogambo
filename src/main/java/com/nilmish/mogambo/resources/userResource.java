package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.dao.UserDAO;
import com.nilmish.mogambo.entities.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by nilesh.m on 15/06/15.
 */

@Path("/user")
public class UserResource {
    @Inject
    private UserDAO userDAO;

    @Path("/save")
    @POST
    public void saveUser(User user){
        userDAO.save(user);
    }
}
