package com.itheima.interceptor;

import com.itheima.pojo.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //看login
        String requestURI = request.getRequestURI();
        //放行？
        if(requestURI.contains("login")){
            log.info("登录请求，放行");
            return true;
        }
        //获取toke
        String token = request.getHeader("token");
        //如果存在，验证
        if(token==null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return false;
        }
        try{
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌解析失败，响应401");
            response.setStatus(401);
            return  false;
        }
        //校验通过，放行
        return true;
    }
}









