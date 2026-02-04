package com.itheima.controller;

import com.github.pagehelper.Page;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result clazzPageSearch(String name, LocalDate begin,LocalDate end,Integer page,Integer pageSize){
        //int不传是默认值0，干扰null判断
        ClazzQueryParam clazzQueryParam=new ClazzQueryParam(name,begin,end,page,pageSize);
        log.info("我的传递参数#################### page={},pagesize={}",page,pageSize);
        log.info("我的传递参数#################### {}",clazzQueryParam);
        //        PageResult<Clazz> classList(name,begin,end,page,pageSize){
        //
        //        }
        PageResult<Clazz> clazzList=clazzService.clazzPageSearch(clazzQueryParam);
        log.info("返回的clazz列表{}",clazzList);
        return Result.success(clazzList);
    }

    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级");
        clazzService.addClazz(clazz);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        log.info("根据id:{}查询",id);
        Clazz clazz=clazzService.queryById(id);
        return Result.success(clazz);
    }
    @PutMapping
    public Result update(@RequestBody Clazz newClazz){
        log.info("更新数据");
        clazzService.update(newClazz);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id:{}删除",id);
        clazzService.deleteById(id);
        return Result.success();
    }
    @GetMapping("/list")
    public Result queryAllClass(){
        log.info("查询所有班级");
        List<Clazz> clazzList= clazzService.queryAllClass();
        return Result.success(clazzList);
    }
}
