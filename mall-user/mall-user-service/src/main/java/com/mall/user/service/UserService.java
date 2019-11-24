package com.mall.user.service;

import com.mall.user.pojo.User;

/**
 * program: spring-cloud-mall->UserService
 * description: 用户服务接口方法
 * author: gerry
 * created: 2019-11-25 00:24
 **/
public interface UserService {

    /**
     * 用户验证
     * @param username
     * @param password
     * @return
     */
    User queryUser(String username, String password);


    /**
     * 根据用户名修改密码
     * @param username
     * @param newPassword
     * @return
     */
    boolean updatePassword(String username,String oldPassword,String newPassword);
}
