package ss.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ss.enums.ErrorCodes;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2838546396581117936L;
    /**
     * 错误码
     */
    private int errorCode = 500;
    /**
     * 错误信息
     */
    private String errorMsg;

    public BaseException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static BaseException of(int errorCode, String errorMsg) {
        return new BaseException(errorCode, errorMsg);
    }

    public static BaseException biz(String errorMsg) {
        return new BaseException(ErrorCodes.BIZ.getCode(), errorMsg);
    }

    public static BaseException ErrorCodes(ErrorCodes errorCodes) {
        return new BaseException((errorCodes.getCode()), errorCodes.getDescription());
    }
}
