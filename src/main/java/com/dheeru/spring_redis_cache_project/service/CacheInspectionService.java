package com.dheeru.spring_redis_cache_project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheInspectionService {

    @Autowired
    private CacheManager cacheManager;


    public void inspectCaches(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            System.out.println("Cache name: " + cache.getName());
            System.out.println("Cache size: " + cache.getNativeCache());
        }else {
            System.out.println("Cache not found: " + cacheName);
        }
    }
}
