package com.itheima.service.impl;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLog> queryOperateLog() {
         List<OperateLog> pageResult=operateLogMapper.queryOperateLog();
         Long total=operateLogMapper.queryOperateLogTotal();
         return new PageResult<>(total, pageResult);
    }
}
