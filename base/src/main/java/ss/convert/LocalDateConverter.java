package ss.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import ss.exception.BaseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式化统一处理
 *
 * @author tjr
 */
public class LocalDateConverter implements Converter<String, LocalDate> {
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public LocalDate convert(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        value = value.trim();
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT));
        } catch (Exception e) {
            throw new BaseException(1000, String.format("日期时间 %s 格式不正确", value));
        }
    }
}