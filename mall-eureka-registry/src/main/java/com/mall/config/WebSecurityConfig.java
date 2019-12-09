package com.mall.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * program: spring-cloud-mall->WebSecurityConfig
 * description: 配置CSRF token才能访问
 * author: gerry
 * created: 2019-12-09 20:55
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 默认情况下添加SpringSecurity依赖的应用每个请求都需要添加CSRF token才能访问，
     * Eureka客户端注册时并不会添加，所以需要配置/eureka/**路径不需要CSRF token。
     *
     * 配置文件中需要修改注册中心地址格式
     * http://${username}:${password}@${hostname}:${port}/eureka/
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
