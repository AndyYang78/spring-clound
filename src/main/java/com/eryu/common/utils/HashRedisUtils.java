package com.eryu.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by lihui on 2017/8/1.
 */
@Component
public class HashRedisUtils<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    public Set<String> keys(String key) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        return ops.keys(key);
    }

    public List<T> values(String key) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        return ops.values(key);
    }

    public boolean hasKey(String key, String hashKey) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();

        return ops.hasKey(key, hashKey);
    }

    public T get(String key, String hashKey) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        return ops.get(key, hashKey);
    }

    public void put(String key, String hashKey, T value) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        ops.put(key, hashKey, value);
    }

    public void delete(String key, String... hashKey) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        ops.delete(key, hashKey);
    }

    public List<T> multiGet(String key, Collection<String> hashKeys) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        return ops.multiGet(key, hashKeys);
    }

    public Long size(String key) {
        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        return ops.size(key);
    }

    public List<T> getPage(String key, int page, int pageSize) {
        if (page < 1)
            page = 1;

        HashOperations<String, String, T> ops = redisTemplate.opsForHash();
        Long size = ops.size(key);
        int start = (page - 1) * pageSize;
        int end = page * pageSize;
        if (start < size) {
            Set<String> keys = ops.keys(key);
            List<String> listKeys = keys.stream().collect(Collectors.toList());
            listKeys = listKeys.subList(start, Math.min(end, size.intValue()));

            return ops.multiGet(key, listKeys);
        }
        return Collections.EMPTY_LIST;
    }
}
