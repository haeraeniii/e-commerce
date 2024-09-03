package hhplus.e_commerce.support.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CacheConfig {

    private final LettuceConnectionFactory connectionFactory;

    @Bean
    public CacheManager cacheManager() {
        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(cacheConfig())
                .withInitialCacheConfigurations(customConfigurationMap())
                .build();
    }

    private  RedisCacheConfiguration cacheConfig() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofMinutes(1L));
    }

    private Map<String, RedisCacheConfiguration> customConfigurationMap() {
        Map<String, RedisCacheConfiguration> customConfigurationMap = new HashMap<>();
        customConfigurationMap.put("product", cacheConfig().entryTtl(Duration.ofMinutes(60L)));
        customConfigurationMap.put("productDetail", cacheConfig().entryTtl(Duration.ofMinutes(60L)));
        customConfigurationMap.put("topProduct", cacheConfig().entryTtl(Duration.ofMinutes(60L)));
        customConfigurationMap.put("order", cacheConfig().entryTtl(Duration.ofDays(1L)));
        return customConfigurationMap;
    }
}
