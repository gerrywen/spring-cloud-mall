package com.mall.common.redis.constant;

/**
 * program: spring-cloud-mall->RedisKey
 * description: redis的key统一存放，避免重复
 * author: gerry
 * created: 2019-11-25 00:32
 **/
public class RedisKey {
    /**
     * access token
     */
    public static final String STRING_ACCESS_TOKEN = "api:string_access_token";

    /**
     * 用户手机
     */
    public static final String USER_CODE_PHONE_KEY_PREFIX = "user:code:phone";

    /**
     * 用户信息
     */
    public static final String USER_INFO_KEY_PREFIX = "mall:user:info";
}
