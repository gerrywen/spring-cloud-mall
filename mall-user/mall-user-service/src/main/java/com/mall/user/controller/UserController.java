package com.mall.user.controller;

import com.mall.admin.model.UmsMember;
import com.mall.user.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
