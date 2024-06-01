package ss.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ss.common.api.ApiResponse;
import ss.common.utils.ApplicationUtils;
import ss.enums.ErrorCodes;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常捕捉处理
 *
 * @author tjr
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = BaseException.class)
    public ApiResponse baseExceptionHandler(HttpServletRequest req, Exception e) {
        BaseException be = (BaseException) e;
        logger.error(e.getMessage(), e);
        return new ApiResponse(be.getErrorCode(), be.getErrorMsg());
    }

    @ExceptionHandler(value = {InvalidFormatException.class, HttpMessageNotReadableException.class})
    public ApiResponse invalidFormatExceptionHandler(HttpServletRequest req, Exception e) {
        logger.error(e.getMessage(), e);
        return new ApiResponse(1000, "无效的数据类型");
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResponse exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error(e.getMessage(), e);
        String message = "服务器有点忙,请稍候重试";
        if ("dev".equals(ApplicationUtils.getProfilesActive()) ||
                "test".equals(ApplicationUtils.getProfilesActive())) {
            message = e.getMessage() == null ? "" : e.getMessage();
        }
        int code = 500;
        if (message.contains("JsonMappingException")) {
            code = 1000;
            message = "无效的数据类型";
        }
        return new ApiResponse(code, message);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ObjectError firstError = e.getBindingResult().getAllErrors().get(0);
        logger.error(e.getMessage(), e);
        return new ApiResponse(ErrorCodes.ILLEGAL_PARAMETER.getCode(), firstError.getDefaultMessage());
    }

}
