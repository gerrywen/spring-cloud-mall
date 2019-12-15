package com.mall.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * program: spring-cloud-mall->SmsServiceApplication
 * description:
 * author: gerry
 * created: 2019-12-13 21:58
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@ComponentScan(basePackages = {"com.mall"})
@MapperScan(basePackages = {"com.mall.admin.mapper","com.mall.oms.mapper"})// 扫描mapper包
public class OmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OmsServiceApplication.class, args);
    }
}
