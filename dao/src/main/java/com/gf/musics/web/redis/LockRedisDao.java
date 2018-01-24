package com.gf.musics.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by lokey on 2017/8/31.
 */
@Component(value = "lockRedisDao")
public class LockRedisDao {
    private final String LOCK_REDIS_PREFIX = "lockState:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setUserOrder(Integer lockId,Integer state){
        redisTemplate.opsForValue().set(getLockStateName(lockId),String.valueOf(state));
    }

    public Object getUserOrder(Integer lockId){
        return redisTemplate.opsForValue().get(getLockStateName(lockId));
    }

    private String getLockStateName(Integer lockId){
        return LOCK_REDIS_PREFIX + lockId;
    }

}
