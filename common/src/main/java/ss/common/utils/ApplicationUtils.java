package ss.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 动态读取配置文件
 *
 * @author tjr
 */
@Slf4j
public class ApplicationUtils {
    private static String profileActive = "dev";

    private final static String argsName = "spring.profiles.active";

    public static void run(Class<?> primarySource, String[] args) {
//        getProfilesActive(args);
        String active = System.getProperty(argsName);
        if (!ObjectUtils.isEmpty(active)) {
            profileActive = active;
        }
        log.info("profileActive = {}", profileActive);
        SpringApplication.run(primarySource, args);
    }

    private static void getProfilesActive(String[] args) {
        log.info("args length = {}", args.length);
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            log.info("arg:{}", arg);
            if (arg.contains(argsName)) {
                String[] arr = StringUtils.split(arg, "=");
                if (arr.length > 0) {
                    profileActive = arr[1];
                    break;
                }
            }
        }
    }

    public static String getProfilesActive() {
        return profileActive;
    }
}
