package com.itheima.controller;

import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClazzDataKV;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private StudentMapper studentMapper;


    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption= reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String,Object>> genderList=reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("获取学生学位信息");
        List<Map<String,Object>> degreeList=reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
    @GetMapping("studentCountData")
    public Result getStudentCountData(){
        log.info("获取学生班级人数信息");
        ClazzDataKV studentCountData=reportService.getStudentClassCountData();
        return Result.success(studentCountData);
    }
}




















