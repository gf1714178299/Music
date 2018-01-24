package com.gf.musics.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by lokey on 2017/8/31.
 */
@Component(value = "producerRedisDao")
public class ProducerRedisDao {
    private final String PRODUCE_REDIS_PREFIX = "producer:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setProducer(String lockNo){
        redisTemplate.opsForValue().set(getProducerName(lockNo),String.valueOf((new Date()).getTime()));
    }

    public Object getProducer(String lockNo){
        return redisTemplate.opsForValue().get(getProducerName(lockNo));
    }

    private String getProducerName(String lockNo){
        return PRODUCE_REDIS_PREFIX + lockNo;
    }

}
