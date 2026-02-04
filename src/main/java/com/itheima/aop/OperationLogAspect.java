package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        OperateLog log = new OperateLog();

        log.setOperateEmpId(getCurrentUserId());
        log.setClassName(joinPoint.getTarget().getClass().getName());
        log.setMethodName(joinPoint.getSignature().getName());

        // 参数转字符串（简单 toString，不序列化）
        Object[] args = joinPoint.getArgs();
        log.setMethodParams(args == null ? "null" : java.util.Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed(); // 执行原方法
            log.setReturnValue(result == null ? "null" : result.toString());
        } catch (Exception e) {
            log.setReturnValue("ERROR: " + e.getMessage());
            throw e; // 异常继续抛出
        } finally {
            log.setOperateTime(LocalDateTime.now());
            log.setCostTime(System.currentTimeMillis() - start);
            operateLogMapper.insert(log); // 直接保存，不 try-catch（失败就报错，方便排查）
        }

        return result;
    }

    private Integer getCurrentUserId(){
        return CurrentHolder.getCurrentId();
    }
}