package ss.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微服务之间的调用
 *
 * @author tjr
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer feignRetryer() {
        //负载均衡默认轮询
        //默认不进行重试
        return Retryer.NEVER_RETRY;
    }
}
