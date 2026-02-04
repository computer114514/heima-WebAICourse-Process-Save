package com.itheima.service.impl;

import com.itheima.exception.ExistChildException;
import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }
    @Override
    public int update(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.update(dept);
    }

    @Override
    public void deleteById(Integer id) {
        int deptCount = deptMapper.IsExistMapper(id);
        if(deptCount>0){
            throw new ExistChildException(4000,"This department have Linked employees");
        }
        deptMapper.deleteById(id);
    }
    //作为Service的bean对象


    @Override
    public void add(Dept dept) {
//        1,补全基础属性
            dept.setCreateTime(LocalDateTime.now());
            dept.setUpdateTime(LocalDateTime.now());
//        2,调用Mapper方法
            deptMapper.Insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }
}























