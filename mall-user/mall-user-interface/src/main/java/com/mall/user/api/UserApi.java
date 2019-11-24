package com.mall.user.api;

import com.mall.config.FeignConfig;
import com.mall.user.api.hystrix.UserApiHystrix;
import com.mall.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * program: spring-cloud-mall->UserApi
 * description: 用户接口
 * author: gerry
 * created: 2019-11-25 00:05
 **/
@FeignClient(value = "mall-user", fallback = UserApiHystrix.class, configuration = FeignConfig.class)
@RequestMapping("user")
public interface UserApi {
    /**
     * 用户验证
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    User queryUser(@RequestParam("username")String username, @RequestParam("password")String password);
}
