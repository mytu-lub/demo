package ss.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ss.common.utils.JacksonUtils;

@ApiModel("API通用返回数据")
public class ApiResponse<T> {

    @ApiModelProperty(value = "返回代码，0表示成功，其他失败")
    private int code = 0;

    @ApiModelProperty(value = "加密状态返回代码，0表示不加密，1表示加密")
    private int status = 0;

    @ApiModelProperty("提示信息,供报错时使用")
    private String message = "成功";

    @ApiModelProperty("返回的数据")
    private T data;

    private static ApiResponse<?> success = new ApiResponse<>();

    public static ApiResponse success() {
        return success;
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> res = new ApiResponse<T>();
        res.setData(data);
        return res;
    }

    public static ApiResponse internalError() {
        ApiResponse res = new ApiResponse();
        res.setCode(500);
        res.setMessage("服务器忙，请稍后试试");
        return res;
    }

    public static ApiResponse internalError(int code, String message, String parameter) {
        ApiResponse res = new ApiResponse();
        res.setCode(code);
        res.setMessage(message);
        res.setData(parameter);
        return res;
    }


    public static ApiResponse fail(String message) {
        ApiResponse res = new ApiResponse();
        res.setCode(1);
        res.setMessage(message);
        return res;
    }

    public ApiResponse() {

    }

    public ApiResponse(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, String message,int status) {
        super();
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public ApiResponse(T data) {
        super();
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public <T> T getData(Class<T> classType) {
        String dataJson = JacksonUtils.toJson(this.data);
        return JacksonUtils.toObject(dataJson, classType);
    }
}
