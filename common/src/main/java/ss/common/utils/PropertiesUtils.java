package ss.common.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * 获取配置文件的参数
 *
 * @author tujr
 */
public class PropertiesUtils {

    private static Properties props = new Properties();

    private static String getActiveProfile() {
        return ApplicationUtils.getProfilesActive();
    }

    static {
        try {
            ClassPathResource cpr = new ClassPathResource("cloud.properties");
            if (cpr.exists()) {
                props.load(cpr.getInputStream());

                String profiles = getActiveProfile();

                cpr = new ClassPathResource("cloud-" + profiles + ".properties");
                Properties _pro = new Properties();
                _pro.load(cpr.getInputStream());

                props.putAll(_pro);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static Integer getValueAsInteger(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static int getInt(String key) {
        return Integer.valueOf(getProperty(key));
    }
}
