package za.co.lindaring.gay.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String GAY_USER_CACHE = "GAY_USER_CACHE";

    @Bean
    public GuavaCacheManager cacheManager() {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(30, TimeUnit.MINUTES);

        GuavaCacheManager cacheManager = new GuavaCacheManager(GAY_USER_CACHE);
        cacheManager.setCacheBuilder(cacheBuilder);

        return cacheManager;
    }

}
