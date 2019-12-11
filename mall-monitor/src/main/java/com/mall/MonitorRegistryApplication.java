package com.mall;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * program: spring-cloud-mall->MonitorRegistryApplication
 * description:
 * author: gerry
 * created: 2019-12-11 19:54
 **/
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class MonitorRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorRegistryApplication.class, args);
    }
}
