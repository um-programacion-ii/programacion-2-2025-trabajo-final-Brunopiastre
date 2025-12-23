package ar.edu.um.ticketflow.proxy.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSeatService {
    private final StringRedisTemplate redisTemplate;

    public RedisSeatService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getEventStateRaw(String eventId) {
        String key = "evento_" + eventId;
        return redisTemplate.opsForValue().get(key);
    }
}
