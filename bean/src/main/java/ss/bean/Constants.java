package ss.bean;

public interface Constants {

    /**
     * APP端token失效时间，单位秒
     */
    Long TOKENAPPTIME =60 * 60L;

    /**
     * redis APP端 token前缀
     */
    String TOKEN_APP_REDIS_KEY_PRE = "app:login:";

    /**
     * redis APP端 新设备登录后，旧token前缀
     */
    String TOKEN_APP_REDIS_NEW_DEVICE_KEY_PRE = "app:new:device:login:";


    /**
     * app用户id header key
     */
    String USERAPPRHEADERUSERID = "Authorization";

    //------------------------------------------------------------------

    /**
     * 登录过期code
     */
    int SESSION_TIME_OUT = -1;

    /**
     * 其他设备登录的code
     */
    int OTHER_DEVICE_LOGIN = -2;

}
