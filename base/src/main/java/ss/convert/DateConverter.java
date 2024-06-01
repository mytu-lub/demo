package ss.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import ss.exception.BaseException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换类
 *
 * @author Administrator
 */
public class DateConverter implements Converter<String, Date> {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public Date convert(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        value = value.trim();
        try {
            if (value.contains("-")) {
                SimpleDateFormat formatter;
                if (value.contains(":")) {
                    //yyyy-MM-dd HH:mm:ss 格式
                    formatter = new SimpleDateFormat(DATE_FORMAT);
                } else {
                    //yyyy-MM-dd 格式
                    formatter = new SimpleDateFormat(SHORT_DATE_FORMAT);
                }
                return formatter.parse(value);
            }
        } catch (Exception e) {
            throw new BaseException(1000, String.format("日期时间 %s 格式不正确", value));
        }
        throw new BaseException(1000, String.format("日期时间 %s 格式不正确", value));
    }
}
