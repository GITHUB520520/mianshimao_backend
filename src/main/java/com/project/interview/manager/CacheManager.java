package com.project.interview.manager;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 多级缓存
 *
 *
 */
@Component
public class CacheManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    Cache<String, Object> localCache = Caffeine.newBuilder().expireAfterWrite(2,TimeUnit.HOURS).maximumSize(10000).build();

    /**
     * get
     *
     * @param key 唯一键
     */
    public Object get(String key) {
        Object value = localCache.getIfPresent(key);
        if (value != null) {
            return value;
        }
        value = redisTemplate.opsForValue().get(key);
        if (value != null){
            localCache.put(key,value);
        }
        return value;
    }

    /**
     * put
     *
     * @param key 唯一键
     * @return
     */
    public void put(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
        localCache.put(key, value);
    }

    /**
     * delete
     *
     * @param key
     */
    public void delete(String key) {
        localCache.invalidate(key);
        redisTemplate.delete(key);
    }
}
