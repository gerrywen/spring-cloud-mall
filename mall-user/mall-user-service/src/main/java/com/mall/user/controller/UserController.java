package com.mall.user.controller;

import com.mall.admin.model.UmsMember;
import com.mall.auth.vo.UserTokenVO;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.user.properties.JwtProperties;
import com.mall.user.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * program: spring-cloud-mall->UserController
 * description: 用户控制器
 * author: gerry
 * created: 2019-11-25 00:37
 **/
@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UmsMemberService userService;

    /**
     * JWT负载中拿到开头
     */
    private String tokenHead;

    /**
     * 构造方法
     *
     * @param jwtProperties jwt配置文件
     */
    public UserController(JwtProperties jwtProperties) {
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
            @RequestParam("password") String password) {
        String token = this.userService.authentication(username, password);
        if (token == null){
            return Result.error(CodeMsg.BAD_REQUEST);
        }
        UserTokenVO userTokenVO = new UserTokenVO();
        userTokenVO.setToken(token);
        userTokenVO.setTokenHead(tokenHead);
        return Result.success(userTokenVO);
    }
    /**
     * 用户验证
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<UmsMember> queryUser(@RequestParam("username")String username, @RequestParam("password")String password){
        UmsMember umsMember = this.userService.queryUser(username, password);
        if (umsMember == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(umsMember);
    }
}
