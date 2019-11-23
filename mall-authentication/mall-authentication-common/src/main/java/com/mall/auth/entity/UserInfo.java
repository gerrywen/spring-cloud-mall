package com.mall.auth.entity;

/**
 * program: spring-cloud-mall->UserInfo
 * description: 用户信息
 * author: gerry
 * created: 2019-11-23 11:40
 **/
public class UserInfo {

    private Long id;

    private String username;

    public UserInfo() {
    }

    public UserInfo(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
