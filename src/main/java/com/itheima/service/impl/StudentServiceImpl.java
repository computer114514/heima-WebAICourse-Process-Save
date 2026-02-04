package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.exception.ExistChildException;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult<Student> studentPageSearch(StudentQueryParam studentQueryParam) {
        //加入pagehelper
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //紧跟着查询语句
        List<Student> studentRowsData= studentMapper.studentPageSearch(studentQueryParam);
//        studentRowsData.stream().forEach(one->one.setUpdateTime(LocalDateTime.now()));
        Long count=studentMapper.getStudentCountData(studentQueryParam);

        return new PageResult<Student>(count,studentRowsData);
    }

    @Override
    public void deleteStudent(String ids) {
        int[] split = Arrays.stream(ids.split(",")).mapToInt(Integer::parseInt).toArray();
        //集合stream(),数组Arrays.stream(),字符串转数组
        if(split!=null&&split.length!=0){
            studentMapper.deleteById(split);
        }

    }

    @Override
    public void addStudent(Student student) {
        student.setViolationCount(0);
        student.setViolationScore(0);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.addStudent(student);
    }

    @Override
    public Student queryStudent(Integer id) {
        return studentMapper.queryById(id);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void punishBadKid(Integer id, Integer score) {

        studentMapper.punishBadKid(id,score);
    }
}
