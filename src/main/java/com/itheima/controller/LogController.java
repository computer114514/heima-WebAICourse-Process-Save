package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.PageResult;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.pojo.OperateLog;

@RestController
@Slf4j
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result queryLogPage(){
        log.info("查询日志");
        PageResult<OperateLog> operateLog= logService.queryOperateLog();
        return Result.success(operateLog);
    }
}
