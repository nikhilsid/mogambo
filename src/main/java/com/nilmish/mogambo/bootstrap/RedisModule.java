package com.nilmish.mogambo.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by nilesh.m on 03/07/15.
 */
public class RedisModule extends AbstractModule {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final JedisPool pool;

    public RedisModule() {
        pool = new JedisPool(new JedisPoolConfig(), "localhost");
    }

    @Provides
    public JedisPool getJedisPool(){
        return pool;
    }

    @Override
    protected void configure() {
    }
}
