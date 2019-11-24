package com.mall.user.api.hystrix;

import com.mall.user.api.UserApi;
import com.mall.user.pojo.User;

/**
 * program: spring-cloud-mall->UserApiHystrix
 * description: 用户接口熔断器
 * author: gerry
 * created: 2019-11-25 00:07
 **/
public class UserApiHystrix implements UserApi {
    @Override
    public User queryUser(String username, String password) {
        return null;
    }
}
