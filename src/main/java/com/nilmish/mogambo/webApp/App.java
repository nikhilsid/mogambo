package com.nilmish.mogambo.webApp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nilmish.mogambo.configuration.MogamboConfiguration;
import com.nilmish.mogambo.dao.UserPostDAO;
import com.nilmish.mogambo.entities.UserPost;
import com.nilmish.mogambo.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilesh.m on 13/06/15.
 */
public class App extends Application<MogamboConfiguration>{
    public static void main(String[] args) {
        try {
            new App().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(Bootstrap<MogamboConfiguration> bootstrap) {

    }

    @Override
    public void run(MogamboConfiguration mogamboConfiguration, Environment environment) throws Exception {
        MogamboModule mogamboModule=new MogamboModule(mogamboConfiguration.getDbConfig());
        Injector injector= Guice.createInjector(mogamboModule);
        registerResources(environment,injector);
        UserPostDAO userPostDAO=injector.getInstance(UserPostDAO.class);
        List<String> a=new ArrayList<String>();
        a.add("1");
        a.add("10");
        a.add("11");
        a.add("12");
        List<String> b=new ArrayList<String>();
        b.add("98");
        b.add("981");
        b.add("988");
        b.add("980");
        userPostDAO.findFeed(a,b);
    }

    private void registerResources(Environment environment,Injector injector){
        environment.jersey().register(injector.getInstance(UserResource.class));
    }

}
