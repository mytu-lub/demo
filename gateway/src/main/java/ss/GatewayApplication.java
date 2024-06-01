package ss;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import ss.common.cache.CacheService;
import ss.common.cache.StringRedisCacheService;
import ss.common.utils.ApplicationUtils;

/**
 * @author DELL
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        ApplicationUtils.run(GatewayApplication.class, args);
    }

    @Bean
    public CacheService cacheService(StringRedisTemplate template) {
        return new StringRedisCacheService(template);
    }

}
