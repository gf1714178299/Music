package com.gf.musics.web.redis;

/**
 * Created by baby on 2017/10/25.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class BaseRedisDao {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplateForSale;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
