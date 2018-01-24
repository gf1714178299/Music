package com.gf.musics.web.redis;

/**
 * Created by baby on 2017/10/25.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.TimeUnit;


@Repository
public class VersionRedisDao extends BaseRedisDao {

    @Autowired
    RedisService redisService;

    private final String PRE_VERSION_PREFIX = "PreVersion:";

    public final static String REDIS_GROUP_VERSION_KEY = "versionGroup";

    private final long  VERSION_INFO_HASH_EXPIRE_TIME = 15 * 24 * 60 * 60;

    public void setVersionHash(String clientType, Map map){
        getRedisTemplate().opsForHash().putAll(getPreVersionKey(clientType), map);
        getRedisTemplate().expire(getPreVersionKey(clientType), VERSION_INFO_HASH_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    public Object getVersionHash(String clientType,String key){
        return getRedisTemplate().opsForHash().get(getPreVersionKey(clientType), key);
    }

    public void deletePreVersion(String clientType){
        getRedisTemplate().delete(getPreVersionKey(clientType));
    }


    String getPreVersionKey(String clientType){
        return PRE_VERSION_PREFIX + clientType;
    }

//    public void refreshVersionRedis(Version version)
//    {
//        if (version != null){
//            redisService.set(REDIS_GROUP_VERSION_KEY,PRE_VERSION_PREFIX,version.getMinVersion());
//        }
//    }

}
