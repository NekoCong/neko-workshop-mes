package io.neko.workshop.module.system.dal.redis.common;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

import static io.neko.workshop.module.system.dal.redis.RedisKeyConstants.CAPTCHA_CODE;
import lombok.RequiredArgsConstructor;

/**
 * 验证码的 Redis DAO
 *
 * @author 灵喵工坊
 */
@Repository
@RequiredArgsConstructor
public class CaptchaRedisDAO {

    private final StringRedisTemplate stringRedisTemplate;

    public String get(String uuid) {
        String redisKey = formatKey(uuid);
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public void set(String uuid, String code, Duration timeout) {
        String redisKey = formatKey(uuid);
        stringRedisTemplate.opsForValue().set(redisKey, code, timeout);
    }

    public void delete(String uuid) {
        String redisKey = formatKey(uuid);
        stringRedisTemplate.delete(redisKey);
    }

    private static String formatKey(String uuid) {
        return String.format(CAPTCHA_CODE.getKeyTemplate(), uuid);
    }

}
