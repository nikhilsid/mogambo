package com.nilmish.mogambo.webApp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nilmish.mogambo.auth.*;
import com.nilmish.mogambo.resources.FeedResource;
import com.nilmish.mogambo.resources.ReportingResource;
import com.nilmish.mogambo.scratchpad.userResource;
import com.nilmish.mogambo.utils.GuiceInjector;
import com.nilmish.mogambo.configuration.MogamboConfiguration;
import com.nilmish.mogambo.resources.TagResource;
import com.nilmish.mogambo.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nilesh.m on 13/06/15.
 */
public class App extends Application<MogamboConfiguration>{
    public static final Logger logger = LoggerFactory.getLogger(App.class);
    private Injector injector;
    public static void main(String[] args) {
        try {
            new App().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(Bootstrap<MogamboConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<MogamboConfiguration>() {
            @Override
            public SwaggerBundleConfiguration getSwaggerBundleConfiguration(MogamboConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(MogamboConfiguration mogamboConfiguration, Environment environment) throws Exception {
        MogamboModule mogamboModule=new MogamboModule(mogamboConfiguration);
        injector= Guice.createInjector(mogamboModule);
        GuiceInjector.assignInjector(injector);
        registerResources(environment);
    }

    private void registerResources(Environment environment){
        environment.jersey().register(injector.getInstance(UserResource.class));
        environment.jersey().register(injector.getInstance(AuthResource.class));
        environment.jersey().register(injector.getInstance(TagResource.class));
        environment.jersey().register(injector.getInstance(ReportingResource.class));
        environment.jersey().register(injector.getInstance(FeedResource.class));
        environment.jersey().register(new AccessTokenSecurityProvider<UserSession>(injector.getInstance(AccessTokenAuthenticator.class)));

        environment.lifecycle().manage(injector.getInstance(AccessTokenService.class));
    }

}
