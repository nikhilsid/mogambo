package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.dao.TagDAO;
import com.nilmish.mogambo.entities.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Path("/tag")
public class TagResource {
    private TagDAO tagDAO;

    @Inject
    public TagResource(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Path("/save")
    @POST
    public boolean saveTag(Tag tag){
        tagDAO.save(tag);
        return true;
    }

//    @Path("/getTagsFollowed")
//    @GET
//    public List<Tag> getTagsFollowed(@QueryParam("username") String username){
//        List<>
//    }


}
