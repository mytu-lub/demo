package ss.enums;

import lombok.Getter;

/**
 * 错误编码
 *
 * @author tjr
 */
@Getter
public enum ErrorCodes {
    /**
     * 入参校验异常编码
     */
    ILLEGAL_PARAMETER(1001, "非法参数异常编码"),
    BIZ(1002, "业务异常"),
    CANCEL(3099, "该用户已注销！"),
    BANNED(3098, "该用户已被封号！"),
    BANNED_FOR_POST(4000, "该用户已被禁言！"),
    DELETE(90000, "内容已经删除！"),
    ZERO_PAY(80000, "0元订单，不需要支付！"),
    IMAGE_TOO_LARGE(-1000, "图片过大"),
    BANNED_GIFTS(2000, "禁止给自己送礼! "),

    /**
     * 字典
     */
    DICT_EXISTED(400, "字典已经存在"),
    ERROR_CREATE_DICT(500, "创建字典失败"),
    ERROR_WRAPPER_FIELD(500, "包装字典属性失败"),
    ERROR_CODE_EMPTY(500, "字典类型不能为空"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),
    UPLOAD_ERROR(500, "上传图片出错"),

    /**
     * 权限和数据问题
     */
    DB_RESOURCE_NULL(400, "数据库中没有该资源"),
    NO_PERMITION(405, "权限异常"),
    REQUEST_INVALIDATE(400, "请求数据格式不正确"),
    INVALID_KAPTCHA(400, "验证码不正确"),
    CANT_DELETE_ADMIN(600, "不能删除超级管理员"),
    CANT_FREEZE_ADMIN(600, "不能冻结超级管理员"),
    CANT_CHANGE_ADMIN(600, "不能修改超级管理员角色"),

    /**
     * 账户问题
     */
    USER_ALREADY_REG(401, "该用户已经注册"),
    NO_THIS_USER(400, "没有此用户"),
    USER_NOT_EXISTED(400, "没有此用户"),
    ACCOUNT_FREEZED(401, "账号被冻结"),
    OLD_PWD_NOT_RIGHT(402, "原密码不正确"),
    TWO_PWD_NOT_MATCH(405, "两次输入密码不一致"),

    /**
     * 错误的请求
     */
    MENU_PCODE_COINCIDENCE(400, "菜单编号和副编号不能一致"),
    EXISTED_THE_MENU(400, "菜单编号重复，不能添加"),
    DICT_MUST_BE_NUMBER(400, "字典的值必须为数字"),
    REQUEST_NULL(400, "请求有错误"),
    SESSION_TIMEOUT(400, "会话超时"),
    SERVER_ERROR(500, "服务器异常"),
    SERVER_MISS_PARAMETER(400, "缺少必要的参数"),
    SERVER_ERROR_PARAMETER(400, "非法参数"),
    CANT_NOT_SEND_NOTICE(400, "不能发送通知给自己"),
    CANT_NOT_SEND_NOTICE_REPEAT(400, "不能重复发送请求"),
    CANT_NOT_SEND_NOTICE_FOCUS(400, "不能重复关注"),

    /**
     * token异常
     */
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(700, "token验证失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR(700, "签名验证失败"),

    /**
     * 版本异常
     */
    VERSION_ERROR(400, "当前版本未上传版本文件"),
    VERSION_DOWNLOAD_ERROR(500, "文件下载失败"),
    VERSION_RELEASE_ERROR(500, "未上传版本"),

    /**
     * 其他
     */
    AUTH_REQUEST_ERROR(400, "账号密码错误");

    private final int code;
    private final String description;

    ErrorCodes(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
