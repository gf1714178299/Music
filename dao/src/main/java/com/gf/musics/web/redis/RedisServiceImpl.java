package com.gf.musics.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by baby on 2017/10/25.
 */
@Component(value = "RedisService")
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void set(String groupKey, String key, Object object)
    {
        redisTemplate.opsForHash().put(groupKey, key, object);
    }
    @Override
    public Object get(String groupKey, String key) {
        Object object = redisTemplate.opsForHash().get(groupKey, key);
        return object;
    }
}
