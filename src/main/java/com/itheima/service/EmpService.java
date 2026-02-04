package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);
    //EmpService逻辑层，
//    PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender,LocalDate begin, LocalDate end);
    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> queryAllEmp();

    LoginInfo login(Emp emp);
}
















