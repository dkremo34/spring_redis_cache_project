package com.dheeru.spring_redis_cache_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CacheRedisMonitorService {
    private final RedisTemplate<String, Object> redisTemplate;
    
    public Set<String> getAllCacheKeys() {

        Set<String> keys = redisTemplate.keys("*");
        return keys;
    }
    
    public Object getCacheValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    public void deleteCacheEntry(String key) {
        redisTemplate.delete(key);
    }
    
    public void clearAllCache() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
}