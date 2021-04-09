package com.hyjiangd.webstore.config;


import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport{

	
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// to prevent class casting exception caused by devtools
		// since devtools would use restartClassLoader for POJO in the project, but the object after deserialized from redis cache would be loaded by base ClassLoader
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig(classLoader);
		RedisCacheWriter cacheWriter = RedisCacheWriter.lockingRedisCacheWriter(factory);
		RedisCacheManager manager = new RedisCacheManager(cacheWriter, redisCacheConfiguration);
		
		return manager;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		
		return template;
	}
}
