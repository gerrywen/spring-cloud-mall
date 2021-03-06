package com.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class XyConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(XyConfigApplication.class,args);
    }
}
