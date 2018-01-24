package com.gf.musics.web.redis;

import com.gf.musics.web.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by lokey on 2017/8/31.
 */
@Component(value = "orderRedisDao")
public class OrderRedisDao {

    private final String BOND_ORDER_NO_REDIS_PREFIX = "bondOrderNo:";

    private final String CHARGE_ORDER_NO_REDIS_PREFIX = "chargeOrderNo:";

    private final String PARKING_ORDER_NO_REDIS_PREFIX = "parkingOrderNo:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    //获取充值订单编码
    public Long getChagreNum(Date date){
        Long num = redisTemplate.opsForValue().increment(getChargeOrderNo(TimeUtil.dateString(date)),1);
        if(num == 1){
            redisTemplate.expire(getChargeOrderNo(TimeUtil.dateString(date)),1, TimeUnit.DAYS);
        }
        return  num;
    }
    //获取押金订单编码
    public Long getBondNum(Date date){
        Long num = redisTemplate.opsForValue().increment(getBondOrderNo(TimeUtil.dateString(date)),1);
        if(num == 1){
            redisTemplate.expire(getBondOrderNo(TimeUtil.dateString(date)),1, TimeUnit.DAYS);
        }
        return  num;
    }
    //获取停车订单号
    public Long getParkingNum(Date date){
        Long num = redisTemplate.opsForValue().increment(getParkingOrderNo(TimeUtil.dateString(date)),1);
        if(num == 1){
            redisTemplate.expire(getParkingOrderNo(TimeUtil.dateString(date)),1, TimeUnit.DAYS);
        }
        return  num;
    }

    private String getBondOrderNo(String date){
        return BOND_ORDER_NO_REDIS_PREFIX + date;
    }

    private String getChargeOrderNo(String date){
        return CHARGE_ORDER_NO_REDIS_PREFIX + date;
    }

    private String getParkingOrderNo(String date){
        return PARKING_ORDER_NO_REDIS_PREFIX + date;
    }

}
