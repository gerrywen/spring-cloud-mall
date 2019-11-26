package com.mall.properties;

/**
 * 配置数据信息
 * @author wenguoli
 * @date 2019/11/26 13:47
 */
public class RedisEnv {
    //   主机地址
    static final String host = "47.98.184.122";

    //端口
    static final int port = 63791;

    //密码没有不填写
    static final String password = "";

    // Redis数据库索引（默认为0）
    static final int database = 0;

    //连接超时时间（毫秒）
    static final String timeOut = "20000ms";

    //最大活跃连接
    static final int maxActive = 8;

    //连接池最大阻塞等待时间（使用负值表示没有限制）
    static final String maxWait = "-1ms";

    //连接池中的最大空闲连接
    static final int maxIdle = 8;

    //连接池中的最小空闲连接
    static final int minIdle = 0;

    static final String nodes = "47.98.184.122:63791";

    static final String maxRedirects = "6";
}
