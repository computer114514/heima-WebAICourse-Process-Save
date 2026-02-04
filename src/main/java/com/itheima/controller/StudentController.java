package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result studentPageSearch(String name,Integer degree, Integer clazzId,Integer page,
                                    Integer pageSize){
        //int不传是默认值0，干扰null判断
        StudentQueryParam studentQueryParam=new StudentQueryParam(name,degree,clazzId,page,pageSize);
        //        PageResult<Clazz> classList(name,begin,end,page,pageSize){
        //
        //        }
        PageResult<Student> studentList=studentService.studentPageSearch(studentQueryParam);
        log.info("返回的clazz列表{}",studentList);
        return Result.success(studentList);
    }
    @DeleteMapping("/{ids}")
    public Result DeleteStudent(@PathVariable String ids){
        log.info("传入的id:{}",ids);
        studentService.deleteStudent(ids);
        return Result.success();
    }
    @PostMapping
    public Result addStudent(@RequestBody Student student){
        log.info("传入的student：{}",student);
        studentService.addStudent(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result queryStudent(@PathVariable Integer id){
        log.info("传入的studentid：{}",id);
        Student student=studentService.queryStudent(id);
        return Result.success(student);
    }
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生数据");
        studentService.update(student);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result pulishBadKid(@PathVariable Integer id,@PathVariable Integer score){
        log.info("不听话的淘气鬼，就要扣分咯");
        studentService.punishBadKid(id,score);
        return Result.success();
    }
}
