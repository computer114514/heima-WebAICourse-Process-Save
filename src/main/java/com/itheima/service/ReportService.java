package com.itheima.service;

import com.itheima.pojo.ClazzDataKV;
import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    //统计员工人数
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzDataKV getStudentClassCountData();
}





























