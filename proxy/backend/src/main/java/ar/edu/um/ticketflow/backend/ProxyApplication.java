package ar.edu.um.ticketflow.proxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Value("${spring.data.redis.host}")
  private String redisHost;

  @Value("${spring.data.redis.port}")
  private int redisPort;

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    // Conectamos al Redis de la Cátedra
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate() {
    RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory());
    // Usamos String porque la cátedra guarda los asientos como "010101..."
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new StringRedisSerializer());
    return template;
  }
}
