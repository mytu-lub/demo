package ss.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * uuid工具
 *
 * @author tjr
 */
public interface UUIDUtils {
    static String get() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    static String getRandom() {
        return get() + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }

    static String getRandomNumber() {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10);
            code = code + r;
        }
        return code;
    }
}
