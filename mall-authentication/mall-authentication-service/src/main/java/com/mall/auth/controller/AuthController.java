package com.mall.auth.controller;

import com.mall.auth.properties.JwtProperties;
import com.mall.auth.service.AuthService;
import com.mall.auth.vo.UserTokenVO;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * program: spring-cloud-mall->AuthController
 * description: 登录授权控制器
 * author: gerry
 * created: 2019-11-25 00:52
 **/
@RestController
@RequestMapping("")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * JWT存储的请求头
     */
    private String tokenHeader;

    /**
     * JWT负载中拿到开头
     */
    private String tokenHead;

    /**
     * 构造方法
     *
     * @param jwtProperties jwt配置文件
     */
    public AuthController(JwtProperties jwtProperties) {
        this.tokenHeader = jwtProperties.getTokenHeader();
        this.tokenHead = jwtProperties.getTokenHead();
    }
    /**
     * 登录授权
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public Result<UserTokenVO> authentication(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        //1.登录校验
        String token = this.authService.authentication(username,password);
        if (StringUtils.isBlank(token)){
            return Result.error(CodeMsg.UNAUTHORIZED);
        }

        UserTokenVO userTokenVO = new UserTokenVO();
        userTokenVO.setToken(token);
        userTokenVO.setTokenHead(tokenHead);
        return Result.success(userTokenVO);
    }
}
