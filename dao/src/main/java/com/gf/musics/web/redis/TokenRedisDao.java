package com.gf.musics.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by lokey on 2017/8/31.
 */
@Component(value = "tokenRedisDao")
public class TokenRedisDao {

    private final String USER_TOKEN_REDIS_PREFIX = "userToken:";

    private final String WORKER_TOKEN_REDIS_PREFIX = "workerToken:";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    //user's method
    public void saveUserToken(Integer userId,String code){
        redisTemplate.opsForValue().set(getUserCodeKey(userId), code, 15, TimeUnit.DAYS);
    }

    public String getUserToken(Integer userId){
        return redisTemplate.opsForValue().get(getUserCodeKey(userId));
    }

    //worker's method
    public void saveWorkerToken(Integer workerId,String code){
        redisTemplate.opsForValue().set(getWorkerCodeKey(workerId), code, 15, TimeUnit.DAYS);
    }

    public String getWorkerToken(Integer workerId){
        return redisTemplate.opsForValue().get(getWorkerCodeKey(workerId));
    }

    private String getUserCodeKey(Integer userId){
        return USER_TOKEN_REDIS_PREFIX + userId;
    }

    private String getWorkerCodeKey(Integer workerId){return WORKER_TOKEN_REDIS_PREFIX + workerId;}
}
