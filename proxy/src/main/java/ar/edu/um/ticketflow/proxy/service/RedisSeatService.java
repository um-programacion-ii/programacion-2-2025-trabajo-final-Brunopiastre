package ar.edu.um.ticketflow.proxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSeatService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String obtenerAsientos(Long eventoId) {
        // Ajustamos la clave segun el formato estandar
        String key = "evento:" + eventoId;
        String mapa = redisTemplate.opsForValue().get(key);
        return mapa;
    }
}
