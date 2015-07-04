package com.nilmish.mogambo.webApp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nilmish.mogambo.auth.*;
import com.nilmish.mogambo.bootstrap.MogamboModule;
import com.nilmish.mogambo.bootstrap.RedisModule;
import com.nilmish.mogambo.resources.*;
import com.nilmish.mogambo.utils.GuiceInjector;
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
        RedisModule redisModule=new RedisModule();
        injector= Guice.createInjector(mogamboModule, redisModule);
        GuiceInjector.assignInjector(injector);
        registerResources(environment);
    }

    private void registerResources(Environment environment){
        environment.jersey().register(injector.getInstance(ApiExceptionMapper.class));
        environment.jersey().register(injector.getInstance(UserResource.class));
        environment.jersey().register(injector.getInstance(UserPostResource.class));
        environment.jersey().register(injector.getInstance(AuthResource.class));
        environment.jersey().register(injector.getInstance(TagResource.class));
        environment.jersey().register(injector.getInstance(ReportingResource.class));
        environment.jersey().register(injector.getInstance(FeedResource.class));
        environment.jersey().register(new AccessTokenSecurityProvider<UserSession>(injector.getInstance(AccessTokenAuthenticator.class)));

        environment.lifecycle().manage(injector.getInstance(AccessTokenService.class));
    }

}
