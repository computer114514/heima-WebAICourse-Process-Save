package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClazzDataKV;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
//        1,empMapper获得原始数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
//        2,组装
        List<Object> jobList= list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> dataList= list.stream().map(dataMap->dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }

    @Override
    public ClazzDataKV getStudentClassCountData() {
        String[] getClazzList= studentMapper.getClazzList();
        Integer[] getDataList=studentMapper.getDataList();

        return new ClazzDataKV(getClazzList,getDataList);

    }
}




















