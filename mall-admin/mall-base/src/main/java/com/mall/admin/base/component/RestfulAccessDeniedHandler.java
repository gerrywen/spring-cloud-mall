package com.mall.admin.base.component;


import cn.hutool.json.JSONUtil;
import com.mall.admin.base.api.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * program: chengjie-ts->RestfulAccessDeniedHandler
 * description:  当访问接口没有权限时，自定义的返回结果。用来解决认证过的用户访问无权限资源时的异常
 * author: gerry
 * created: 2019-08-07 17:20
 **/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
