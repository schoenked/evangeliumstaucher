package de.evangeliumstaucher.app.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import de.evangeliumstaucher.app.component.JpaCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

    @Bean
    @Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(200)
                .maximumSize(500)
                .expireAfterWrite(14, TimeUnit.DAYS)
                .weakKeys()
                .recordStats());
        return cacheManager;
    }

    @Bean
    public CacheManager databaseCacheManager(JpaCacheManager jpaCacheManager) {
        return jpaCacheManager;
    }
}