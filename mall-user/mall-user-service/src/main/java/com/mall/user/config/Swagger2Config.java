package com.mall.user.config;

import com.mall.user.properties.JwtProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * program: spring-cloud-mall->Swagger2Config
 * description: swagger文档
 * author: gerry
 * created: 2019-11-26 00:27
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 获取配置文件数据
     */
    private final JwtProperties jwtProperties;

    /**
     * 构造方法
     *
     * @param jwtProperties jwt配置文件
     */
    public Swagger2Config(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mall.user"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .globalOperationParameters(setHeaderToken())
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户商城系统")
                .description("用户接口文档")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        //设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey(jwtProperties.getTokenHeader(), jwtProperties.getTokenHeader(), "header");
        result.add(apiKey);
        return result;
    }

    /**
     * 设置头部token
     *
     * @return list
     */
    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(jwtProperties.getTokenHeader())
                .description(jwtProperties.getTokenHeader() + ":" + jwtProperties.getTokenHead() + " " + "token value")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());
        return pars;
    }

    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/user/.*"));
        result.add(getContextByPath("/auth/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference(jwtProperties.getTokenHeader(), authorizationScopes));
        return result;
    }
}
