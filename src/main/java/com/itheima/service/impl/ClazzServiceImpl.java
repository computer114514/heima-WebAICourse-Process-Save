package com.itheima.service.impl;

import com.itheima.exception.ExistChildException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> clazzPageSearch(ClazzQueryParam clazzQueryParam) {
        //1,条件搜索之一。
        //1,获得多少条数据
        long count=clazzMapper.getClazzCount();
//        log.info("查询多少个课程获得的数字{}",count);
        //2，获得class列表
        List<Clazz> clazz = clazzMapper.getClazz(clazzQueryParam);

//        clazz.stream().map(oneClazz->oneClazz.setStatus(getStatus(LocalDate.now(),oneClazz.getEndDate(),oneClazz.getBeginDate())));
        //map流是转换，是创建一个新的数组

        clazz.stream().forEach(oneClazz->oneClazz.setStatus(getStatus(LocalDate.now(),oneClazz.getEndDate(),
                oneClazz.getBeginDate())));
        //而foreach是改。
        return new PageResult<Clazz>(count,clazz);

    }
    public String getStatus(LocalDate now,LocalDate end,LocalDate begin){
        if(now.isBefore(begin)){
            //现在在结课之后，说明已经结课了
            return "未开班";
        }else if(now.isAfter(end)){
            return "已结课";
        }else{
            return "在读中";
        }
    }

    @Override
    public void addClazz(Clazz clazz) {
        //添加班级service层
        //1，先添加基本属性:创建时间，修改时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz queryById(Integer id) {
        return clazzMapper.queryById(id);
    }

    @Override
    public void update(Clazz newClazz) {
        clazzMapper.update(newClazz);
    }

    @Override
    public void deleteById(Integer id) {
        int studentCount = clazzMapper.IsExistStudent(id);
        if(studentCount>0){
            throw new ExistChildException(414,"This object have Linked children");
        }
        clazzMapper.deleteById(id);

    }

    @Override
    public List<Clazz> queryAllClass() {
        List<Clazz> ClazzList=clazzMapper.queryAllClass();
        return ClazzList;
    }
}
