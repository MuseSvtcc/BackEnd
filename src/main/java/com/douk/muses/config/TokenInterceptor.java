package com.douk.muses.config;

import com.douk.utils.JWT.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    // 重写 前置拦截方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        // 1、从请求头中获取token
        String token = request.getHeader("Authorization");
        // 2、判断 token 是否存在
        if (token == null || "".equals(token)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\": 302, \"message\": \"未登录\", \"redirect\": \"/user/login\"}");
            return false;
        }
        // 3、解析token
        if(!JWTUtils.validateToken(token)){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\": 302, \"message\": \"未登录\", \"redirect\": \"/user/login\"}");
            return false;
        }
        return true;
    }
}