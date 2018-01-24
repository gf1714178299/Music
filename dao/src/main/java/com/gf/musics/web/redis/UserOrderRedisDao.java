package com.gf.musics.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by lokey on 2017/8/31.
 */
@Component(value = "userOrderRedisDao")
public class UserOrderRedisDao {
    private final String ORDER_NO_REDIS_PREFIX = "userOrder:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setUserOrder(Integer userId){
        redisTemplate.opsForValue().set(getUserOrderName(userId),String.valueOf((new Date()).getTime()));
    }

    public Object getUserOrder(Integer userId){
        return redisTemplate.opsForValue().get(getUserOrderName(userId));
    }

    private String getUserOrderName(Integer userId){
        return ORDER_NO_REDIS_PREFIX + userId;
    }

}
