package com.mall.auth.service.impl;

import com.mall.auth.client.UserClient;
import com.mall.auth.entity.UserInfo;
import com.mall.auth.properties.JwtProperties;
import com.mall.auth.service.AuthService;
import com.mall.auth.utils.JwtUtils;
import com.mall.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * program: spring-cloud-mall->AuthServiceImpl
 * description: 权限接口实现类
 * author: gerry
 * created: 2019-11-25 00:45
 **/
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public String authentication(String username, String password) {
        try{
            //1.调用微服务查询用户信息
            User user = this.userClient.queryUser(username,password);
            //2.查询结果为空，则直接返回null
            if (user == null){
                return null;
            }
            //3.查询结果不为空，则生成token
            return JwtUtils.generateToken(new UserInfo(user.getId(), user.getUsername()),
                    jwtProperties.getExpiration(), jwtProperties.getSecret());

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
