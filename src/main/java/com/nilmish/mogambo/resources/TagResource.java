package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.dao.TagDAO;
import com.nilmish.mogambo.entities.Tag;
import io.dropwizard.auth.Auth;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {
    private TagDAO tagDAO;

    @Inject
    public TagResource(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Path("/save")
    @POST
    public boolean saveTag(@Auth UserSession userSession, Tag tag){
        tagDAO.save(tag);
        return true;
    }

    @Path("/getAllTags")
    @GET
    public List<Tag> getTagsFollowed(@Auth UserSession userSession){
        List<ObjectId> objectIdList=tagDAO.findIds();
        return tagDAO.getTagsFromIds(objectIdList);
    }


}
