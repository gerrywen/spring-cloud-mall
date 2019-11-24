package com.mall.auth.controller;

import com.mall.auth.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * program: spring-cloud-mall->AuthController
 * description: 登录授权控制器
 * author: gerry
 * created: 2019-11-25 00:52
 **/
@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 登录授权
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<Void> authentication(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        //1.登录校验
        String token = this.authService.authentication(username,password);
        if (StringUtils.isBlank(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //2.将token写入cookie，并指定httpOnly为true，防止通过js获取和修改

        return ResponseEntity.ok().build();
    }
}
