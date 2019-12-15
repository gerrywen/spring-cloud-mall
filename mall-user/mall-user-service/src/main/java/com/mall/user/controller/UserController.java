package com.mall.user.controller;

import com.mall.admin.model.UmsMember;
import com.mall.auth.vo.UserTokenVO;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.user.properties.JwtProperties;
import com.mall.user.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * program: spring-cloud-mall->UserController
 * description: 用户控制器
 * author: gerry
 * created: 2019-11-25 00:37
 **/
@RestController
@Api(value = "UserController", tags = "member-会员等级管理")
@RequestMapping("")
public class UserController {
    @Autowired
    private UmsMemberService memberService;

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
    public UserController(JwtProperties jwtProperties) {
        this.tokenHeader = jwtProperties.getTokenHeader();
        this.tokenHead = jwtProperties.getTokenHead();
    }

    @PostMapping("register")
    @ApiOperation("会员注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = false, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "string", paramType = "query"),

    })
    public Result register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("phone") String telephone,
                                 @RequestParam("code") String authCode) {
        return memberService.register(username, password, telephone, authCode);
    }

    /**
     * 登录授权
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    @ApiOperation("会员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = false, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "query"),
    })
    public Result<UserTokenVO> authentication(@RequestParam("username") String username,
                                              @RequestParam("password") String password) {
        String token = this.memberService.authentication(username, password);
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
    @ApiOperation("用户验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = false, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "query"),
    })
    public ResponseEntity<UmsMember> queryUser(@RequestParam("username")String username, @RequestParam("password")String password){
        UmsMember umsMember = this.memberService.queryUser(username, password);
        if (umsMember == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(umsMember);
    }

    @GetMapping("info")
    @ApiOperation("获取获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result<UmsMember> getUserInfo() {
        return Result.success(memberService.getCurrentMember());
    }


    @GetMapping("code")
    @ApiOperation("获取验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = false, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "string", paramType = "query"),
    })
    public Result getAuthCode(@RequestParam("phone") String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @PutMapping("password")
    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "string", paramType = "query"),
    })
    public Result updatePassword(@RequestParam("phone") String telephone,
                                       @RequestParam("password") String password,
                                       @RequestParam("auth_code") String authCode) {
        return memberService.updatePassword(telephone,password,authCode);
    }

    @GetMapping("refresh_token")
    @ApiOperation(value = "刷新token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = memberService.refreshToken(token);
        if (refreshToken == null) {
            return Result.error(CodeMsg.TOKEN_OVER);
        }
        UserTokenVO userTokenVO = new UserTokenVO();
        userTokenVO.setToken(refreshToken);
        userTokenVO.setTokenHead(tokenHead);

        return Result.success(userTokenVO);
    }
}
