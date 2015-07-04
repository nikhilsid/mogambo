package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.entities.UserPost;
import com.nilmish.mogambo.feed.FeedGenerator;
import com.nilmish.mogambo.utils.GuiceInjector;
import com.wordnik.swagger.annotations.Api;
import io.dropwizard.auth.Auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by nilesh.m on 28/06/15.
 */

@Api("/feed")
@Path("/feed")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedResource {

    @Inject private FeedGenerator feedGenerator;

    public FeedResource() {
        GuiceInjector.getInjector().injectMembers(this);
    }

    @Path("/getFeed")
    @GET
    public List<UserPost> getFeed(@Auth UserSession userSession){
        String username=userSession.getUsername();
        return feedGenerator.generateFeed(username);
    }
}
