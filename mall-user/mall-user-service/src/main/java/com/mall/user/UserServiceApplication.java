package com.mall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * program: spring-cloud-mall->UserApplication
 * description: 用户启动器
 * author: gerry
 * created: 2019-11-25 00:22
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mall.user.mapper")
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class,args);
    }
}
