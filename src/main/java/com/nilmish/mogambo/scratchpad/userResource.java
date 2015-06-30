package com.nilmish.mogambo.scratchpad;

import com.google.inject.Inject;
import com.nilmish.mogambo.utils.GuiceInjector;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by nilesh.m on 27/06/15.
 */

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class userResource {
    @Inject private userdao userdao;

    public userResource() {
        GuiceInjector.getInjector().injectMembers(this);
    }

    @Path("save")
    @POST
    public boolean saveUser(user user){
        userdao.save(user);
        return true;
    }

    @Path("get")
    @GET
    public user getUser(@QueryParam("id") String id){
        return userdao.get(new ObjectId(id));
    }
}
