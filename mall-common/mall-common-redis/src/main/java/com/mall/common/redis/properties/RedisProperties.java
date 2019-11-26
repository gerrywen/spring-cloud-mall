package com.mall.common.redis.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->RedisProperties
 * description:
 * author: gerry
 * created: 2019-11-26 20:46
 **/
@Component
@Data
public class RedisProperties {
    public String host = RedisEnv.host;

    public int port = RedisEnv.port;

    public String password = RedisEnv.password;

    public int database = RedisEnv.database;

    public String timeOut = RedisEnv.timeOut;


    public int maxActive = RedisEnv.maxActive;

    public String maxWait = RedisEnv.maxWait;

    public int maxIdle = RedisEnv.maxIdle;

    public int minIdle = RedisEnv.minIdle;

    public String nodes = RedisEnv.nodes;

    public String maxRedirects = RedisEnv.maxRedirects;


}