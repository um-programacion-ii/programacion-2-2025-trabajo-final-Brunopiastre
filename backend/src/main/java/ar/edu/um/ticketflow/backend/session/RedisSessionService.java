package ar.edu.um.ticketflow.backend.session;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisSessionService {

    private final RedisTemplate<String, Object> redis;

    public RedisSessionService(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    public void saveSession(String key, SessionData data) {
        redis.opsForValue().set(key, data, Duration.ofMinutes(10));
    }

    public SessionData getSession(String key) {
        Object result = redis.opsForValue().get(key);
        return result instanceof SessionData sd ? sd : null;
    }

    public void deleteSession(String key) {
        redis.delete(key);
    }
}
