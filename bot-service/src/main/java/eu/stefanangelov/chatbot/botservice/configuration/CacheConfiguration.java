package eu.stefanangelov.chatbot.botservice.configuration;

import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@RequiredArgsConstructor
@EnableCaching
@Configuration
public class CacheConfiguration {

	private final ObjectMapper objectMapper;

	@Bean
	public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory, @Value("${spring.cache.redis.time-to-live}") Duration ttl) {
		RedisSerializationContext.SerializationPair<Object> jsonSerializer =
				RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
		return RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisConnectionFactory)
				.cacheDefaults(
						RedisCacheConfiguration.defaultCacheConfig()
								.entryTtl(ttl)
								.serializeValuesWith(jsonSerializer)
				)
				.build();
	}

}
