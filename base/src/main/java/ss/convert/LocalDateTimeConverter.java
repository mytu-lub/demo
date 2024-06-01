package ss.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import ss.exception.BaseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式化统一处理  时分秒
 *
 * @author tjr
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public LocalDateTime convert(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        value = value.trim();
        try {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (Exception e) {
            throw new BaseException(1000, String.format("日期时间 %s 格式不正确", value));
        }
    }
}
