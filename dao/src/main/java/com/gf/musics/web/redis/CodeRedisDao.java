package com.gf.musics.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by lokey on 2017/8/31.
 */
@Component(value = "codeRedisDao")
public class CodeRedisDao {
    private final String WORKER_CODE_REDIS_PREFIX = "workerCode:";

    private final String USER_CODE_REDIS_PREFIX = "userCode:";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveUserCode(String phone,String code){
        redisTemplate.opsForValue().set(getUserCodeKey(phone), code, 15, TimeUnit.MINUTES);
    }

    public void saveWorkerCode(String phone,String code){
        redisTemplate.opsForValue().set(getWorkerCodeKey(phone), code, 15, TimeUnit.MINUTES);
    }

    public String getUserCode(String phone){
        return redisTemplate.opsForValue().get(getUserCodeKey(phone));
    }

    public String getWorkerCode(String phone){
        return redisTemplate.opsForValue().get(getWorkerCodeKey(phone));
    }

    private String getWorkerCodeKey(String phone){
        return WORKER_CODE_REDIS_PREFIX + phone;
    }

    private String getUserCodeKey(String phone){
        return USER_CODE_REDIS_PREFIX + phone;
    }
}
