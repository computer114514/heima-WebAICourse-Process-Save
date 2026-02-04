package com.itheima.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DemoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //拦截请求后执行多次
        log.info("开始执行过滤器....成功拦截");

        //0,获取路径
        //1,观察路径是否含有login
        //2,如果含有login，就放行,如果没有，看token
        //3,处理，解析token

        //pass
        filterChain.doFilter(servletRequest,servletResponse);

        log.info("放行之后");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //web启动的时候，执行一次
        log.info("初始化过滤器");
        }

    @Override
    public void destroy() {
        log.info("销毁过滤器");
    }
}












