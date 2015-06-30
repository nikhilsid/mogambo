package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.dao.ReportInappropriateDAO;
import com.nilmish.mogambo.utils.GuiceInjector;
import io.dropwizard.auth.Auth;
import org.bson.types.ObjectId;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by nilesh.m on 27/06/15.
 */
public class ReportingResource {
    @Inject
    private ReportInappropriateDAO reportInappropriateDAO;

    public ReportingResource() {
        GuiceInjector.getInjector().injectMembers(this);
    }

    @POST
    @Path("/report")
    public boolean reportPost(@Auth UserSession userSession,@QueryParam("postId") ObjectId id){
        String username=userSession.getUsername();
        reportInappropriateDAO.reportInappropriate(username,id);
        return true;
    }

}
