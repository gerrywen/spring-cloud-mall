package com.mall.gateway.filter;

import com.mall.auth.properties.JwtProperties;
import com.mall.auth.utils.JwtUtils;
import com.mall.gateway.config.FilterProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * program: spring-cloud-mall->LoginFilter
 * description: 登录拦截器
 * author: gerry
 * created: 2019-11-25 22:50
 **/
@Component
public class LoginFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        //1.获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //2.获取request
        HttpServletRequest request = context.getRequest();
        //3.获取路径
        String requestUri = request.getRequestURI();
        logger.info(requestUri);
        //4.判断白名单
        return !isAllowPath(requestUri);
    }

    /**
     * 过滤不需要验证的路由
     * @param requestUri
     * @return
     */
    private boolean isAllowPath(String requestUri) {
        //1.定义一个标记
        boolean flag = false;

        //2.遍历允许访问的路径
        List<String> paths = Arrays.asList(this.filterProperties.getAllowPaths().split(" "));
        for (String path : paths){
            if (requestUri.startsWith(path)){
                flag = true;
                break;
            }
        }

        logger.info("JwtInterceptor当前请求的URI是==>{},isMatch==>{}", requestUri, flag);
        //1.获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //2.获取request
        HttpServletRequest httpRequest = context.getRequest();
        if (flag) {
            return flag;
        } else if ("OPTIONS".equals(httpRequest.getMethod().toUpperCase())) {
            // 如果OPTIONS请求自动让它通过
            return true;
        } else if (requestUri.toLowerCase().endsWith(".jpg") || requestUri.toLowerCase().endsWith(".png") ||
                requestUri.toLowerCase().endsWith(".ico") || requestUri.toLowerCase().endsWith(".gif")) {
            return true;
        }
        return flag;
    }

    @Override
    public Object run() throws ZuulException {
        String tokenHeader = jwtProperties.getTokenHeader();
        String tokenHead = jwtProperties.getTokenHead();
        String secret = jwtProperties.getSecret();

        //1.获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //2.获取request
        HttpServletRequest httpRequest = context.getRequest();
        HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpRequest);
        // JWT存储的请求头
        String authHeader = requestWrapper.getHeader(tokenHeader);
        // 过滤逻辑：判断是否有值，前缀是否正确
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());// The part after "Bearer "
            //4.校验
            try{
                //4.1 校验通过，放行
                JwtUtils.getUserInfoFromToken(authToken, secret);
            }catch (Exception e){
                //4.2 校验不通过，返回403
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                // 返回信息
                context.getResponse().setContentType("application/json;charset=UTF-8");
                context.setResponseBody(String.format(SessionContants.ERROR_UFORBIDDEN_BODY, "token校验失败"));
            }
        } else {
            //4.2 未授权，返回401
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            // 返回信息
            context.getResponse().setContentType("application/json;charset=UTF-8");
            context.setResponseBody(String.format(SessionContants.ERROR_UNAUTHORIZED_BODY, "参数头不足"));
        }

        return null;
    }
}
