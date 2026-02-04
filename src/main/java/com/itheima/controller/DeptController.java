package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log=LoggerFactory.getLogger(DeptController.class);

    //controller自动把数组对象转化成json
    @Autowired
    private DeptService deptService;
    //DI部分知识，就是pinia，把方法对象做成对象放入仓库，autowired自动塞入

    @GetMapping
    public Result list(){
//        System.out.println("select all");
        log.info("select all");

        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        int count=deptService.update(dept);
        System.out.println("update"+count);
        return Result.success(count);
    }
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id=Integer.parseInt(idStr);
//        System.out.println("根据id删除部门"+id);
//        return Result.success();
//    }
//    public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
//        System.out.println("根据id删除部门"+deptId);
//        return Result.success();
//    }//你甚至可以省略@RequestParam，当传入参数和形式参数一致的时候\
    @Log
    @DeleteMapping
    public Result delete(Integer id){
        log.info("id删除:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("根据ID"+deptId);
//        return Result.success();
//    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        System.out.println("根据ID"+id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }
}



















