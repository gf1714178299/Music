package com.gf.musics.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by lokey on 2017/8/31.
 */
@Component(value = "comsumerRedisDao")
public class ComsumerRedisDao {
    private final String COMSUMER_REDIS_PREFIX = "comsumerTime:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setComsumerOrderTime(Integer lockId,Long time){
        redisTemplate.opsForValue().set(getComsumerTimeName(lockId),String.valueOf(time));
    }

    public String getComsumerOrderTime(Integer lockId){
        return redisTemplate.opsForValue().get(getComsumerTimeName(lockId));
    }

    private String getComsumerTimeName(Integer lockId){
        return COMSUMER_REDIS_PREFIX + lockId;
    }

}
