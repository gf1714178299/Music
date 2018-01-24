package com.gf.musics.web.redis;

/**
 * Created by baby on 2017/10/25.
 */
public interface RedisService {
    void  set(String groupKey, String key, Object object);

    Object get(String groupKey, String key);
}
