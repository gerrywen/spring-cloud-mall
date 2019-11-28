package com.mall.admin.base.component;

import cn.hutool.json.JSONUtil;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * program: chengjie-ts->RestAuthenticationEntryPoint
 * description: 当未登录或者token失效访问接口时，自定义的返回结果。用来解决匿名用户访问无权限资源时的异常
 * author: gerry
 * created: 2019-08-07 17:20
 **/
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Result.error(new CodeMsg(403,authException.getMessage()))));
        response.getWriter().flush(); // 刷新(flush)缓冲的输出流，其实flush()也是通过out.write()将数据写入底层输出流的。
    }
}
