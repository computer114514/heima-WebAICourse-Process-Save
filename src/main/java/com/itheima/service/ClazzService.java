package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> clazzPageSearch(ClazzQueryParam clazzQueryParam);

    void addClazz(Clazz clazz);

    Clazz queryById(Integer id);

    void update(Clazz newClazz);

    void deleteById(Integer id);

    List<Clazz> queryAllClass();
}
