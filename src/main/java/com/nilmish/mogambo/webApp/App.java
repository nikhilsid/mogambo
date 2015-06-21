package com.nilmish.mogambo.webApp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nilmish.mogambo.auth.*;
import com.nilmish.mogambo.configuration.MogamboConfiguration;
import com.nilmish.mogambo.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by nilesh.m on 13/06/15.
 */
public class App extends Application<MogamboConfiguration>{
    public static final Logger logger = LoggerFactory.getLogger(App.class);
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
    }

    private void registerResources(Environment environment,Injector injector){
        environment.jersey().register(injector.getInstance(UserResource.class));
        environment.jersey().register(injector.getInstance(AuthResource.class));
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");
        AccessTokenService accessTokenService=new RedisAccessTokenService(jedisPool);
        AccessTokenAuthenticator accessTokenAuthenticator = new AccessTokenAuthenticator(accessTokenService);
        environment.jersey().register(new AccessTokenSecurityProvider<UserSession>(accessTokenAuthenticator));
    }

}
