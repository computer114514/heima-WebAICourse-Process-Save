package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

public interface StudentService {


    PageResult<Student> studentPageSearch(StudentQueryParam studentQueryParam);

    void deleteStudent(String ids);

    void addStudent(Student student);

    Student queryStudent(Integer id);

    void update(Student student);

    void punishBadKid(Integer id, Integer score);
}
