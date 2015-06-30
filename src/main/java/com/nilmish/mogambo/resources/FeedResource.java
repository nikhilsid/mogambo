package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.entities.UserPost;
import com.nilmish.mogambo.feed.FeedGenerator;
import com.nilmish.mogambo.utils.GuiceInjector;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by nilesh.m on 28/06/15.
 */
public class FeedResource {

    @Inject private FeedGenerator feedGenerator;

    public FeedResource() {
        GuiceInjector.getInjector().injectMembers(this);
    }

//    @Path("/getFeed")
//    @GET
//    public List<UserPost> getFeed(@Auth UserSession userSession){
//        String username=userSession.getUsername();
//        return feedGenerator.generateFeed(username);
//    }
}
