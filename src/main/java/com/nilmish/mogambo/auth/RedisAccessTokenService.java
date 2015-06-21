package com.nilmish.mogambo.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by nilesh.m on 12/05/15.
 */
public class RedisAccessTokenService implements AccessTokenService {

    private JedisPool jedisPool;
    private ObjectMapper objectMapper=new ObjectMapper();
    public static final Logger LOG = LoggerFactory.getLogger(RedisAccessTokenService.class);

    @Inject
    public RedisAccessTokenService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public UserSession createAccessToken(UserSession userSession) {
        String userName=userSession.getUsername();
        Jedis jedis = jedisPool.getResource();
        try {
            String userNameKey = getUserNameKey(userName);
            String accessToken;
            if (jedis.exists(userNameKey)) {
                LOG.info("Found an existing auth token for userName: "+userName+". Reusing it.");
                accessToken = jedis.get(userNameKey);
                storeAgentSession(userSession);
                userSession.setAccessToken(accessToken);
            } else {
                LOG.info("Creating a new auth token for userName: "+userName);
                String newAccessToken = UUID.randomUUID().toString();
                jedis.set(userNameKey, newAccessToken);
                jedis.set(getAccessTokenKey(newAccessToken), userName);
                accessToken = newAccessToken;
                storeAgentSession(userSession);
                userSession.setAccessToken(accessToken);
            }
        } finally {
            jedis.close();
        }
        return userSession;
    }


    private String getAccessTokenKey(String newAccessToken) {
        return "atToUn:"+newAccessToken;
    }

    private void storeAgentSession(UserSession userSession) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String userName = userSession.getUsername();
                String agentJson = objectMapper.writeValueAsString(userSession);
                String key = getAgentSessionKey(userName);
                jedis.set(key, agentJson);
            } finally {
                jedis.close();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAgentSessionKey(String userName) {
        return "unToAs:"+userName;
    }


    private String getUserNameKey(String userName) {
        return "unToAt:"+userName;
    }

    public UserSession getUserFromAccessToken(String accessToken) {
        String userName=getUserNameFromAccessToken(accessToken);
        Jedis jedis = jedisPool.getResource();
        try {
            if (jedis.exists(getAgentSessionKey(userName))) {
                String agentJson = jedis.get(getAgentSessionKey(userName));
                try {
                    UserSession userSession = objectMapper.readValue(agentJson, UserSession.class);
                    userSession.setAccessToken(accessToken);
                    return userSession;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("Inconsistency in Jedis. AccessToken exists as key: " + getAccessTokenKey(accessToken) + " but AgentSession key absent: " + getAgentSessionKey(userName));
            }
        } finally {
            jedis.close();
        }

    }

    private String getUserNameFromAccessToken(String accessToken) {
        Jedis jedis = jedisPool.getResource();
        try {
            if (jedis.exists(getAccessTokenKey(accessToken))) {
                return jedis.get(getAccessTokenKey(accessToken));
            } else {
                return null;
            }
        } finally {
            jedis.close();
        }
    }

    public boolean isValidToken(String accessToken) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.exists(getAccessTokenKey(accessToken));
        } finally {
            jedis.close();
        }
    }

    public void removeAccessToken(String accessToken) {
        String userName=getUserNameFromAccessToken(accessToken);
        if(userName==null){
            LOG.error("Username not found for accessToken "+accessToken);
            return;
        }
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(getUserNameKey(userName));
            jedis.del(getAccessTokenKey(accessToken));
            jedis.del(getAgentSessionKey(userName));
            LOG.info("Removed access token: "+accessToken + "for the user with username : "+userName);
        } finally {
            jedis.close();
        }
    }

    public void removeUser(String userName) {
        Jedis jedis = jedisPool.getResource();
        try {
            String userNameKey = getUserNameKey(userName);
            if (jedis.exists(userNameKey)) {
                jedis.del(getUserNameKey(userName));
                String accessToken = jedis.get(userNameKey);
                jedis.del(getAccessTokenKey(accessToken));
                jedis.del(getAgentSessionKey(userName));
            }
            else{
                LOG.error("Username not found: ",userName);
            }
        } finally {
            jedis.close();
        }
    }
}
