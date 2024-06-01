package ss.common.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import ss.common.utils.JacksonUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class StringRedisCacheService implements CacheService {
    private StringRedisTemplate redisTemplate;

    public StringRedisCacheService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(final String key, final Object value) {
        redisTemplate.opsForValue().set(key, JacksonUtils.toJson(value));
    }

    @Override
    public void setLeftPush(final String key, final Object value, final Long liveSeconds) {
        redisTemplate.opsForList().leftPush(key, JacksonUtils.toJson(value));
        redisTemplate.expire(key, liveSeconds, TimeUnit.SECONDS);
    }

    @Override
    public <T> T get(final String key, Class<T> cl) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return JacksonUtils.toObject(value, cl);
    }

    @Override
    public <T> List<T> getList(final String key, Class<T> cl) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return JacksonUtils.toList(value, cl);
    }

    @Override
    public void delete(final String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void set(final String key, final Object value, final Long liveSeconds) {
        redisTemplate.opsForValue().set(key, JacksonUtils.toJson(value), liveSeconds,
                TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public Boolean expire(String key, Long liveSeconds) {
        return redisTemplate.expire(key, liveSeconds.longValue(),
                TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long increment(String key, Long value) {
        return redisTemplate.boundValueOps(key).increment(value);
    }

    @Override
    public Long increment(String key) {
        return redisTemplate.boundValueOps(key).increment(1L);
    }

    /**
     * 批量删除key
     */
    @Override
    public void removePattern(final String pattern) {
        // 获取所有匹配的键
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null && keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

}
