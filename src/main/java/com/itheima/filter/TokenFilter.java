package com.itheima.filter;

import com.itheima.pojo.JwtUtils;
import com.itheima.utils.CurrentHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取路径
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //看login
        String requestURI = request.getRequestURI();
        //放行？
        if(requestURI.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
            return ;
        }
        //获取toke
        String token = request.getHeader("token");
        //如果存在，验证
        if(token==null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return;
        }
        try{
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            //把字符串转换为数字
            CurrentHolder.setCurrentId(empId);
            log.info("当前id:,存入Thread"+empId);
        } catch (Exception e) {
            log.info("令牌解析失败，响应401");
            response.setStatus(401);
            return;
        }
        //校验通过，放行
        filterChain.doFilter(servletRequest,servletResponse);

        //删除Thread中的数据
        CurrentHolder.remove();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
