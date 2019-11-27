package com.mall.gateway.filter;

/**
 * program: spring-cloud-mall->SessionContants
 * description: 会话相关常量
 * author: gerry
 * created: 2019-11-26 00:06
 **/
public interface SessionContants {
    String LOGIC_IS_SUCCESS = "LOGIC_IS_SUCCESS";
    String ERROR_UNAUTHORIZED_BODY = "{\"code\": 401, \"msg\":\"%s\", \"data\":null}";
    String ERROR_UFORBIDDEN_BODY = "{\"code\": 403, \"msg\":\"%s\", \"data\":null}";
}
