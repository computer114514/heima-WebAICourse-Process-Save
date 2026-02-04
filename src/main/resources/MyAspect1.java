package com.itheima.aop;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAspect1 {
    @Before()
    public void before(){
        System.out.println("before...");
    }
}
