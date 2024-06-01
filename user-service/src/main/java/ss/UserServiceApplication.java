package ss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import ss.common.utils.ApplicationUtils;

/**
 * @author tujr
 */
@EnableScheduling
@EnableAsync
@EnableFeignClients
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan("ss.mapper")
public class UserServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ApplicationUtils.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}