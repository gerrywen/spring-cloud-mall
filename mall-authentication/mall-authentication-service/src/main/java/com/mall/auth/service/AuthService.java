package com.mall.auth.service;

/**
 * program: spring-cloud-mall->AuthService
 * description: 权限服务接口
 * author: gerry
 * created: 2019-11-25 00:45
 **/
public interface AuthService {
    /**
     * 用户授权
     * @param username
     * @param password
     * @return
     */
    String authentication(String username, String password);
}
