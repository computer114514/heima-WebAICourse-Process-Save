package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//员工管理controller
@Slf4j //生成日志文件
@RestController
@RequestMapping("/emps")
public class EmpController<T> {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        //pagehelper仅能对紧跟在其后的第一个查询语句分页处理
            log.info("分页查询：{}",empQueryParam);
            PageResult<Emp> pageResult= empService.page(empQueryParam);
            return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        empService.save(emp);

        return Result.success();
    }
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("ids: {}", Arrays.toString(ids));
//        return Result.success();
//    }
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("ids: {}", ids);
        empService.delete(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("员工id查询是:{}",id);
        Emp emp=empService.getInfo(id);
        log.info("查询结果是:{}",emp);
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        //修改员工信息
        empService.update(emp);

        return Result.success();
    }
    @GetMapping("/list")
    public Result queryEmpList(){
        log.info("查询员工列表");
        List<Emp> empList= empService.queryAllEmp();
        return Result.success(empList);
    }
}

























