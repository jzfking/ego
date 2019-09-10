package com.dj.ego.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author 戴俊明
 * @className MailDao
 * @description 邮箱验证码操作
 * @date 2019/8/20 22:37
 **/

@Repository
public class MailCodeManager {

    @Autowired
    RedisUtil redisUtil;

    public void setCode(Integer id, String code) {
        redisUtil.set(String.valueOf(id), code, 5L, TimeUnit.MINUTES);
    }

    public void remove(Integer id) {
        redisUtil.remove(String.valueOf(id));
    }
}
