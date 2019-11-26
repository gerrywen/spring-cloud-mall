package com.mall.properties;

import org.springframework.stereotype.Component;

/**
 * program: spring-cloud-mall->RedisProperties
 * description: redis属性配置
 * author: gerry
 * created: 2019-11-26 12:45
 **/
@Component
public class RedisProperties {
    public String host;

    public int port;

    public String password;

    public int database;

    public String timeOut;


    public int maxActive;

    public String maxWait;

    public int maxIdle;

    public int minIdle;

    public String nodes;

    public String maxRedirects;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = RedisEnv.host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = RedisEnv.port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = RedisEnv.password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = RedisEnv.database;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = RedisEnv.timeOut;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = RedisEnv.maxActive;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = RedisEnv.maxWait;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = RedisEnv.maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = RedisEnv.minIdle;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = RedisEnv.nodes;
    }

    public String getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(String maxRedirects) {
        this.maxRedirects = RedisEnv.maxRedirects;
    }
}
